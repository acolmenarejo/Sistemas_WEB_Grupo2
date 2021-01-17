package navegacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class tematica */
@WebServlet(name="tematica",urlPatterns={"/pyet/tematica"},initParams={@WebInitParam(name="tematica", value="Juegos de rol")})
public class tematica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Object autenticadoObj = session.getAttribute("autenticado");
		//Object autenticadoObj = req.getAttribute("autenticado");
		String tematica = getInitParameter("tematica");
		ServletContext context = req.getServletContext();
		if(autenticadoObj != null && (boolean) autenticadoObj) {
			Connection connection = null;
			Connection connection2 = null;
			Statement statement = null;
			Statement statementB = null;
			//contraseña root:
			String pass = "qwertyuiop1234567890";
			System.out.println(tematica);
			
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
			out.println("<a class='navbar-brand' href='/Proyecto_SW1/pyet/inicio'><img alt='logo'style='width: 40px;' src='/Proyecto_SW1/imagenes/LogoCuadrado.jpg'></a>");
			out.println("<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#collapsibleNavbar'>");
			out.println("<span class='navbar-toggler-icon'></span>");
			out.println("</button>");
			out.println("<div class='collapse navbar-collapse justify-content-between' id='collapsibleNavbar'>");
			out.println("<form class='form-inline'>");
			out.println("<div class='input-group'>");
			out.println("<input class='form-control' id='myInput' type='text' placeholder='Buscar'>");
			out.println("</div>");
			out.println("</form>");
			out.println("<ul class='navbar-nav'>");
			out.println("<li class='nav-item'><a class='nav-link' href='/Proyecto_SW1/appFlow/crearPost_formulario.html'>Crear Post</a></li>");
			out.println("<li class='nav-item'><a class='nav-link' href='/Proyecto_SW1/pyet/misPosts'>Mis posts</a></li>");
			out.println("<li class='nav-item dropdown active'><a\r\n class='nav-link dropdown-toggle' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'> Tem�tica </a>");
			out.println("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/pyet/tematica'>Juegos de rol</a>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/pyet/tematica2'>PCs y electr�nica</a>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/pyet/tematica3'>Pok�mon</a>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/pyet/tematica4'>Web programming</a>");
			out.println("<div class='dropdown-divider'></div>");
			out.println("<a class='dropdown-item' href='/Proyecto_SW1/pyet/inicio'>Todo</a>");
			out.println("</div></li>");
			out.println("<li class='nav-item'><a class='nav-link' href='/Proyecto_SW1/pyet'>Cerrar Sesi�n</a></li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("</nav>");
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				//contraseña root:
				//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "root");
				//contraseña carlos:
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", pass);
				statement = connection.createStatement();
				
				ResultSet rs = null;
				ResultSet resultset = null;
				synchronized(statement) {
					rs = statement.executeQuery("SELECT * from post WHERE tematica='"+ tematica +"' ORDER BY id_post DESC LIMIT 20");
				}
				out.println("<div class='container container-fluid' style='margin-top:80px' id='contenedorPosts'>");
				
				
				while (rs.next()) {
					out.println("<div class='card'>");
					out.println("<div class='card-header bg-success text-white'>");
					out.println("<h3>" + rs.getString("titulo") + "</h3>");
					out.println("</div>");
					out.println("<div class='card-body'>");
					out.println("<p>("+ rs.getString("tematica") + ")</p><br>");
					out.println("<p>"+ rs.getString("contenido") + "</p>");
					
					//HACER: 
					//Revisar que este statement funciona, igual hay que parsear el id_usuario.
					try {
						int id = rs.getInt("id_usuario");
						connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", pass);
						statementB = connection2.createStatement();
						synchronized(statementB) {
						resultset = statementB.executeQuery("SELECT * FROM usuario WHERE id=" + id);
							System.out.println("carga usuario del post");
						}
						if(resultset.next()) {
							out.println("<footer class='blockquote-footer'>"+ resultset.getString("nombreusuario") +"</footer>");
						}
					} catch(Exception e) {
						System.out.println(e);
					}
					
					
					out.println("</div>");
					out.println("</div>");
					out.println("<br>");
				}
				out.println("</div>");
				out.println("<script>");
				out.println("$(document).ready(function(){");
				out.println("$('#myInput').on('keyup', function() {");
				out.println("var value = $(this).val().toLowerCase();");
				out.println("$('#contenedorPosts .card').filter(function() {");
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
			System.out.println("error pantalla tematica");
		}
	}
		
		
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
