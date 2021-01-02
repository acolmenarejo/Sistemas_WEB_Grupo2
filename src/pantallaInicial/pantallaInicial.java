package pantallaInicial;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBDD.Blog;


@WebServlet(name="inicio", urlPatterns= {"/inicio"})
public class pantallaInicial extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object autenticadoObj = req.getAttribute("autenticado");
		ServletContext context = req.getServletContext();
		if(autenticadoObj != null && (boolean) autenticadoObj) {
			/////
			Connection connection = null;
			Statement statement = null;
			ResultSet rs;
			List <Blog> posts = new ArrayList<Blog>();
			/////
			
			
			String nombre = req.getParameter("nombre");
			String contrasena = req.getParameter("contrasena");
			System.out.println("pantalla inicial OK");
		
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "qwertyuiop1234567890");
				statement = connection.createStatement();
				rs = statement.executeQuery("SELECT * from post");
				
				out.println("<html>");
				out.println("<head><title>weChat</title></head>");
				out.println("<body>");
				out.println("<h1>Bienvenido a weChat</h1>");
				
				
				while (rs.next()) {
						//Blog post = new Blog();
						//post.setId(rs.getInt("id_post"));
						//post.setContenido(rs.getString("contenido"));
						//posts.add(post);
						
						out.println("<p>" + rs.getInt("id_post") + "</p>");
						out.println("<p>" + rs.getString("contenido") + "</p>");
				}
				
				out.println("</body></html>");
				//out.println("<img src=\"./formulario1/Paradise.jpg\"></img>");
			
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				
				
				out.close();
			}
		
		} else {
			//RequestDispatcher paginaError = context.getRequestDispatcher("/ejemplos06/error.html");
			//paginaError.forward(req, resp);
			System.out.println("error pantalla inicial");
		}
		
		
		
	}

}
