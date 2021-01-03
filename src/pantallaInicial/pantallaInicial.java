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

@WebServlet(name = "inicio", urlPatterns = { "/inicio" })
public class pantallaInicial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object autenticadoObj = req.getAttribute("autenticado");
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
			
			//Impresión del html:
			
			//HACER:
			//Poner el out print, copiar de home(v4).html desde la primera línea hasta el </nav>
			//Las dejo copiadas ya y la primera hecha, (las comento ahora por no tener todos los errores)
			out.println("<!DOCTYPE html>");
			/*
			<html lang='en'>
			<head>
			<title>Bootstrap Example</title>
			<meta charset='utf-8'>
			<meta name='viewport' content='width=device-width, initial-scale=1'>
			<link rel='stylesheet'
				href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>
			<script
				src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
			<script
				src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js'></script>
			<script
				src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>
			</head>
			<body>

			<nav class='navbar navbar-expand-md bg-dark navbar-dark fixed-top'>
				<a class='navbar-brand' href='#'><img alt='logo'
					style='width: 40px;' src='../imagenes/LogoCuadrado.jpg'></a>
				<button class='navbar-toggler' type='button' data-toggle='collapse'
					data-target='#collapsibleNavbar'>
					<span class='navbar-toggler-icon'></span>
				</button>
				<div class='collapse navbar-collapse justify-content-between'
					id='collapsibleNavbar'>
					<form class='form-inline'>
						<div class='input-group'>
							<input class="form-control" id="myInput" type="text" placeholder='Buscar'>
						</div>
					</form>
					<ul class='navbar-nav'>
						<li class='nav-item'><a class='nav-link'
							href='./crearPost_formulario.html'>Crear Post</a></li>
						<li class='nav-item'><a class='nav-link'
							href='/Proyecto_SW1/misPosts'>Mis posts</a></li>
						<li class='nav-item dropdown'><a
							class='nav-link dropdown-toggle' href='#' id='navbarDropdown'
							role='button' data-toggle='dropdown' aria-haspopup='true'
							aria-expanded='false'> Temática </a>
							<div class='dropdown-menu' aria-labelledby='navbarDropdown'>
								<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=juegosRol'>Juegos de rol</a>
								<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=2'>PCs y electrónica</a>
								<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=3'>Otro tema bro</a>
								<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=4'>Tema 4</a>
								<div class='dropdown-divider'></div>
								<a class='dropdown-item' href='/Proyecto_SW1/tematica?tema=T'>Todo</a>
							</div></li>
						<li class='nav-item'><a class='nav-link' href='#'>Cerrar Sesión</a></li>
					</ul>
				</div>
			</nav>
			*/
			
			
			
			
			
			
			
			//Obtener e imprimir los posts	
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "qwertyuiop1234567890");
				statement = connection.createStatement();
				
				//HACER:
				//Poner al select una limitación (LIMIT 20) o algo así
				rs = statement.executeQuery("SELECT * from post");
				
				out.println("<div class='container container-fluid' style='margin-top:80px'>");
				//Iterador para imprimir los posts
				while (rs.next()) {
					//HACER:
					//Las comillas exteriores tienen que ser " y las interiores ' (al revés de como está ahora / imitando la de encima del while)
					//out.println("...'..'....");
					
					out.println('<div class="card">');
					out.println('<div class="card-header bg-success text-white">');
					out.println('<h3>' + rs.getString("titulo") + '</h3>');
					out.println('</div>');
					out.println('<div class="card-body">');
					out.println('<p>'+ rs.getString("contenido") + '</p>');
					
					//HACER:
					//Revisar que este statement funciona, igual hay que parsear el id_usuario.
					resultset = statement.executeQuery("SELECT * FROM usuario WHERE id=" + rs.getInt("id_usuario"));
					if(resultset.next()) {
						out.println('<footer class="blockquote-footer">'+ resultset.getString("nombreusuario") +'</footer>');
					}
					
					
					out.println('</div>');
					out.println('</div>');
					out.println('<br>');
				}
				out.println("</div>");
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				out.close();
			}
			
			//HACER:
			//Más print, los de cierre, los comento. hay que ponerlo igual que los de antes. Esto irá en todos los siguientes también.
			/*
			<script>
			$(document).ready(function(){
				$("#myInput").on("keyup", function() {
					var value = $(this).val().toLowerCase();
					$("#contenedorPosts .card").filter(function() {
						$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
					});
				});
			});
			</script>
			*/
			out.println("</body></html>");
			
			
		
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
