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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "root");
			//contraseña carlos:
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "qwertyuiop1234567890");
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
					+ " id_post INT NOT NULL AUTO_INCREMENT,\r\n"
					+ " id_usuario INT NOT NULL,\r\n"
					+ " titulo TEXT NOT NULL,\r\n"
					+ " tematica TEXT NOT NULL,\r\n"
					+ " contenido TEXT NOT NULL,\r\n"
					+ " PRIMARY KEY(id_post),\r\n"
					+ " INDEX(id_usuario),\r\n"
					+ " FOREIGN KEY (id_usuario) REFERENCES usuario (id)\r\n"
					+ "     ON DELETE CASCADE\r\n"
					+ "     ON UPDATE NO ACTION\r\n"
					+ ")");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		//Comprobamos la existencia de la cookie. Si existe logueamos directamente al usuario. Si no existe creamos la cookie
		String idSession = null;
		Cookie[] cookies = req.getCookies();
		
		if(cookies!=null) {
		for(int i=0; i<cookies.length; i++)
		{
			if(cookies[i].getName().equals("idUsuarioSession")) {
				
				idSession = cookies[i].getValue(); 
				break;
			}
		  


		}		
		}
		
		*/
		
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
		//Revisar: Posible fallo
			if(resultSet.next() && resultSet.getString("correo").equals(email)){
				//JOptionPane.showMessageDialog(null, "Todo OK todo GUCCI");
				System.out.println("Todo OK todo GUCCI");
						
				RequestDispatcher inicio = context.getNamedDispatcher("inicio");
				
				//Comprobamos que no haya una sesion previa
				HttpSession antiguaSession = req.getSession(false);
				if(antiguaSession != null) {
					antiguaSession.invalidate();
				}
				//Creamos una nueva sesi�n
				HttpSession session = req.getSession();
				
				//Creamos la cookie del usuario
				Cookie cookie= new Cookie("sessionId",String.valueOf(resultSet.getInt("id")));
				resp.addCookie(cookie);	
				
				//req.setAttribute("autenticado", true);
				session.setAttribute("autenticado", true);
				//req.setAttribute("id_usuario", resultSet.getInt("id"));
				//System.out.println(resultSet.getInt("id"));
				session.setAttribute("idUsuarioSesion", resultSet.getInt("id"));
				
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
