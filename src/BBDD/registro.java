package BBDD;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name="registro", urlPatterns= {"/registro"})
public class registro extends HttpServlet{

	private Connection connection = null;
	private Statement statement = null;
	ResultSet resultSet = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//contraseña root:
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "root");
			//contraseña carlos:
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "qwertyuiop1234567890");
			statement = connection.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String correo = req.getParameter("email");
		String nombreusuario = req.getParameter("nombreusuario");
		String contrasena = req.getParameter("contrasena");
		
		ServletContext context = req.getServletContext();
		
		System.out.println(contrasena);

		//String query = "select * from usuario";;
		String mensaje = "";
		try {
			//edad = Integer.parseInt(req.getParameter("edad"));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			//Comprobamos si el usuario existe
			String usuarioExiste = "SELECT * FROM usuario WHERE correo = '"+ correo + "'";
			
			synchronized(statement) {
				resultSet = statement.executeQuery(usuarioExiste);
				
			}
		
			if(!resultSet.next() ){
			
				// Peticion a la BBDD para que inserte los parametros del usuario y lo registre
				String query = "INSERT INTO usuario VALUES( NULL,'" + nombreusuario + "', '" + contrasena + "', '" + correo+ "')";
				synchronized(statement) {
					statement.executeUpdate(query);
				}
			}else {
				
				RequestDispatcher error = context.getRequestDispatcher("/paginasError/error.html");
				error.forward(req, resp);
			}			
			
						
		} catch(Exception e) {
			
			System.out.println(e);
			
			RequestDispatcher error = context.getRequestDispatcher("/paginasError/error.html");
			error.forward(req, resp);
			
			
		}
		
		RequestDispatcher login = context.getNamedDispatcher("login");
		login.forward(req, resp);
		
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
