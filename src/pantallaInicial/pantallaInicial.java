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
import javax.servlet.http.HttpSession;

import BBDD.Blog;

@WebServlet(name = "inicio", urlPatterns = { "/inicio" })
public class pantallaInicial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object autenticadoObj = session.getAttribute("autenticado");
		//Object autenticadoObj = req.getAttribute("autenticado");
		ServletContext context = req.getServletContext();
		if(autenticadoObj != null && (boolean) autenticadoObj) {
			Connection connection = null;
			Statement statement = null;
			ResultSet rs;			
			
			String nombre = req.getParameter("nombre");
			String contrasena = req.getParameter("contrasena");
			System.out.println("pantalla inicial OK");
		
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			
			out.println("<!DOCTYPE html>");
			out.println("<html lang='en'>");
			out.println("<head>");
			out.println("<title>Bootstrap Example</title>");
			out.println("<meta charset='utf-8'>");
			out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
			out.println("<link rel='stylesheet'	href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>");
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js'></script>");
			out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>");
			out.println("</head>");
			out.println("<body>");
			out.println("<nav class='navbar navbar-expand-md bg-dark navbar-dark fixed-top'>");
			out.println("<a class='navbar-brand' href='#'><img alt='logo' style='width: 40px;' src='./imagenes/LogoCuadrado.jpg'></a>");
			out.println("<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#collapsibleNavbar'>");
			out.println("<span class='navbar-toggler-icon'></span>");
			out.println("</button>");
			out.println("<div class='collapse navbar-collapse justify-content-between' id='collapsibleNavbar'>");
			out.println("<form class='form-inline'>");
			out.println("<div class='input-group'>");
			out.println("<input class=\"form-control\" id=\"myInput\" type=\"text\" placeholder='Buscar'>");
			out.println("</div>");
			out.println("</form>");
			out.println("<ul class='navbar-nav'>");
			out.println("<li class='nav-item'><a class='nav-link' href='./appFlow/crearPost_formulario.html'>Crear Post</a></li>");
			out.println("<li class='nav-item'><a class='nav-link' href='/Proyecto_SW1/misPosts'>Mis posts</a></li>");
			out.println("<li class='nav-item dropdown'><a\r\n class='nav-link dropdown-toggle' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'> Tem�tica </a>");
			out.println("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=juegosRol'>Juegos de rol</a>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=2'>PCs y electr�nica</a>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=3'>Otro tema bro</a>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=4'>Tema 4</a>");
			out.println("<div class='dropdown-divider'></div>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=T'>Todo</a>");
			out.println("</div></li>");
			out.println("<li class='nav-item'><a class='nav-link' href='#'>Cerrar Sesi�n</a></li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("</nav>");
							
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				//contraseña root:
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "root");
				//contraseña carlos:
				//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "qwertyuiop1234567890");
				statement = connection.createStatement();
				
				rs = statement.executeQuery("SELECT * from post LIMIT 20");
				
				out.println("<div class='container container-fluid' style='margin-top:80px'>");
				
				while (rs.next()) {

			
					out.println("<div class='card'>");
					out.println("<div class='card-header bg-success text-white'>");
					out.println("<h3>" + rs.getString("titulo") + "</h3>");
					out.println("</div>");
					out.println("<div class='card-body'>");
					out.println("<p>"+ rs.getString("contenido") + "</p>");
					//HACER:
					//Revisar que este statement funciona, igual hay que parsear el id_usuario.
					ResultSet resultset = statement.executeQuery("SELECT * FROM usuario WHERE id=" + rs.getInt("id_usuario"));
					if(resultset.next()) {
						out.println("<footer class='blockquote-footer'>"+ resultset.getString("nombreusuario") +"</footer>");
					}
					
					
					out.println("</div>");
					out.println("</div>");
					out.println("<br>");
				}
				out.println("</div>");
				out.println("<script>");
				out.println("$(document).ready(function(){");
				out.println("$(\"#myInput\").on(\"keyup\", function() {");
				out.println("var value = $(this).val().toLowerCase();");
				out.println("$(\"#contenedorPosts .card\").filter(function() {");
				out.println("$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)");
				out.println("});");
				out.println("});");
				out.println("});");
				out.println("</script>");
				out.println("</body></html>");
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				out.close();
			}	
		
		} else {
			RequestDispatcher error = context.getRequestDispatcher("/paginasError/error.html");
			error.forward(req, resp);
			System.out.println("error pantalla inicial");
		}
		
		
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
