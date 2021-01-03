package BBDD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class login
 */
@WebServlet(name="login", urlPatterns= {"/login"})
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Connection connection = null;
	private Statement statement = null;
	ResultSet resultSet = null;
	
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
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String contrasena = req.getParameter("contrasena");
		ServletContext context = req.getServletContext();
		
		String query = "SELECT * FROM usuario WHERE correo='" + email + "' AND contrasena='" + contrasena + "'";
		System.out.println(query);
		try {
			synchronized(statement) {
				resultSet = statement.executeQuery(query);
				//System.out.println(resultSet.next());
			}
		
			if(resultSet.next() && resultSet.getString("correo").equals(email)){
				//JOptionPane.showMessageDialog(null, "Todo OK todo GUCCI");
				System.out.println("Todo OK todo GUCCI");
				
				RequestDispatcher inicio = context.getNamedDispatcher("inicio");
				req.setAttribute("autenticado", true);
				inicio.forward(req, resp);
			} else {
				//JOptionPane.showMessageDialog(null, "No existe el usuario");
				System.out.println("El ususario o la contraseña no son correctos");

				RequestDispatcher error = context.getRequestDispatcher("/paginasError/error.html");
				error.forward(req, resp);
			}
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	
	/*
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String password = req.getParameter("password");
		ServletContext context = req.getServletContext();
		if(password.equals(passwordAdmin)) {
			RequestDispatcher addPuntuacion = context.getNamedDispatcher("AddPuntuacion");
			req.setAttribute("autenticado", true);
			addPuntuacion.forward(req, resp);
		} else {
			RequestDispatcher error = context.getRequestDispatcher("/ejemplos06/error.html");
			error.forward(req, resp);
		}
	}
	*/
	
	
}
