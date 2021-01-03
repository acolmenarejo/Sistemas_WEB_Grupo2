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

@WebServlet(name = "crearpost", urlPatterns = { "/crearPost" })
public class crearpost extends HttpServlet {
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
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//Lectura de los par�metros del usuario
		String titulo = req.getParameter("titulo");
		String tematica = req.getParameter("tematica");
		String contenido = req.getParameter("contenido");
		ServletContext context = req.getServletContext();
		

//Petici�n que realizamos a la base de datos para que inserte dentro de la tabla de post lo que el usuario ha metido por pantalla
		String query = "INSERTO INTO post VALUES( NULL, '" + req.getAttribute("id_usuario").toString() + "', '" + titulo + "', '" + tematica + "', '" + contenido + "')";


		RequestDispatcher inicio = context.getNamedDispatcher("inicio");
		inicio.forward(req, resp);
	}

}