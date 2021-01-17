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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
			//contraseña root:
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "root");
			//contraseña carlos:
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "qwertyuiop1234567890");
			statement = connection.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
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
						
				
				
				//Comprobamos que no haya una sesion previa
				HttpSession antiguaSession = req.getSession(false);
				if(antiguaSession != null) {
					antiguaSession.invalidate();
				}
				//Creamos una nueva sesi�n
				HttpSession session = req.getSession();
				
				//Creamos la cookie del usuario
				Cookie cookie= new Cookie("autologin",String.valueOf(resultSet.getInt("id")));
				cookie.setMaxAge(60*60*24*30);
				resp.addCookie(cookie);
				
				
				session.setAttribute("autenticado", true);
				session.setAttribute("idUsuarioSesion", resultSet.getInt("id"));
				
				//resp.sendRedirect(req.getContextPath() + "/pyet/inicio");
				RequestDispatcher inicio = context.getNamedDispatcher("inicio");
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

	
	
	
	
}
