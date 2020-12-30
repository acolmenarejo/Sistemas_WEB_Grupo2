package BBDD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="connect", urlPatterns= {"/conexionBBDD"})
public class conexionBBDD extends HttpServlet{

	private Connection connection = null;
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	private Statement statement = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "qwertyuiop1234567890");
			statement = connection.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("connected");
			statement.execute("CREATE TABLE IF NOT EXISTS usuario (\r\n"
					+ " id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\r\n"
					+ " nombreusuario VARCHAR (20) NOT NULL,\r\n"
					+ " contrasena VARCHAR(20) NOT NULL,\r\n"
					+ " correo VARCHAR (100) NOT NULL UNIQUE)");
			
			statement.execute("CREATE TABLE IF NOT EXISTS post (\r\n"
					+ "    id_post INT NOT NULL AUTO_INCREMENT,\r\n"
					+ "    id INT NOT NULL,\r\n"
					+ "    contenido TEXT NOT NULL,\r\n"
					+ "    PRIMARY KEY(id_post),\r\n"
					+ "    INDEX(id),\r\n"
					+ "    FOREIGN KEY (id) REFERENCES usuario (id)\r\n"
					+ "        ON DELETE CASCADE\r\n"
					+ "        ON UPDATE NO ACTION\r\n"
					+ ")");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = resp.getWriter()){
			out.println("<html>");
			out.println("<head><title>weChat</title></head>");
			out.println("<body>");
			out.println("<h1>Contenido en la base de datos</h1>");
			out.println("<ul>");
			
			String query = "select * from usuario";
			ResultSet resultSet = null;
			try {
				synchronized(statement) {
					resultSet = statement.executeQuery(query);
				}
				while(resultSet.next()) {
					out.println("<li>Nombre: " + resultSet.getString("nombreusuario") + ", id:  " 
				+ resultSet.getInt("id") +"</li>");
				}
			} catch(Exception e) {
				System.out.println(e);
			}
			
			out.println("</ul></body></html>");
		}		
	}
	@Override
	public void destroy() {
		try {
			statement.close();
		} catch (Exception e) {
			
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String getServletInfo() {
		return "Contador Cookies";
	}	
	
}
