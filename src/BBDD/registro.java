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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "root");
			statement = connection.createStatement();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		try {		
			System.out.println("connected");
	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String correo = req.getParameter("email");
		String nombreusuario = reg.getParameter("nombreusuario");
		String contraseña = req.getParameter("contrase�a");
		System.out.println(contraseña);
		//String query = "select * from usuario";;
		String mensaje = "";
		try {
			//edad = Integer.parseInt(req.getParameter("edad"));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
		String query = "insert into usuario values( NULL,'" + nombreusuario + "', '" + contraseña + "', '" + correo + "')";
		
		/*
		
			System.out.println("previo0");
			synchronized(statement) {
				System.out.println("previo1");
				resultSet = statement.executeQuery(query);
				System.out.println("previo2");
			}
			while(resultSet.next()) {
				System.out.println("previo4");
				if(query!="NULL" || resultSet.getString("correo").equals(correo)) {
					System.out.println("correo existente");
				}else {
					System.out.println("connected2");
					query = "insert into usuario values( NULL,'A', '" + contraseña + "', '" + correo + "')";
				}
										
				}
			*/
			synchronized(statement) {
				statement.executeUpdate(query);
			}
			
			
			mensaje = "BD ha actualizado correctamente";
		} catch(Exception e) {
			mensaje = "Error al insertar datos";
			System.out.println(e);
		}
		
		resp.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = resp.getWriter()){
			out.println("<html>");
			out.println("<head><title>Registro</title></head>");
			out.println("<body>");
			out.println("<h1>" + mensaje + "</h1>");
			out.println("</body></html>");
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
