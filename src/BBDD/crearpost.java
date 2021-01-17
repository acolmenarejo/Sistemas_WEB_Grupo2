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
import javax.servlet.http.HttpSession;

@WebServlet(name = "crearpost", urlPatterns = { "/crearPost" })
public class crearpost extends HttpServlet {
	/**
	 * 
	 */
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
			String pass = "qwertyuiop1234567890";
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", pass);
			statement = connection.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//Lectura de los parametros del usuario
		System.out.println("[Crear Post] invocado");
		String titulo = req.getParameter("titulo");
		String tematica = req.getParameter("tematica");
		String contenido = req.getParameter("contenido");
		ServletContext context = req.getServletContext();
		//System.out.println((int) req.getAttribute("id_usuario"));
		HttpSession session = req.getSession(false);
		System.out.println(session.getAttribute("idUsuarioSesion"));
		System.out.println(titulo + ' ' + tematica + ' ' + contenido);

//Peticion que realizamos a la base de datos para que inserte dentro de la tabla de post lo que el usuario ha metido por pantalla
		String query = "INSERT INTO post VALUES(NULL, " + session.getAttribute("idUsuarioSesion") + ", '" + titulo + "', '" + tematica + "', '" + contenido + "')";

		try {
			synchronized(statement) {
				statement.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher inicio = context.getNamedDispatcher("inicio");
		inicio.forward(req, resp);
	}

}