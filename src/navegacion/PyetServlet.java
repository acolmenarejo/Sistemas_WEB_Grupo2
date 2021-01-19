package navegacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="pyet", urlPatterns= {"/pyet-home"})
public class PyetServlet extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//contraseña root:
	String pass = "qwertyuiop1234567890";
	private Connection connection = null;
	private Statement statement = null;
	ResultSet resultSet = null;
	
	private static String jdbcDriver = "com.mysql.jdbc.Driver";
    private static String dbName = "p2";

	@Override
		public void init() throws ServletException {
			super.init();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				//connection = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "root");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/", "root", pass);
				statement = connection.createStatement();
				System.out.println("connected");
				statement.execute("CREATE DATABASE IF NOT EXISTS p2");
				statement.execute("use p2");
				statement.execute("CREATE TABLE IF NOT EXISTS usuario (\r\n"
						+ " id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\r\n"
						+ " nombreusuario VARCHAR (30) NOT NULL,\r\n"
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
	
	
	
	
	
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>pyet.es</title>");
			out.println("<meta charset=\"utf-8\">");
			out.println("meta name='viewport' content='width=device-width, initial-scale=1'>");
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>");
			out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
			out.println("<style>");
			out.println("body {");
			out.println("position: relative;");
			out.println("}");
			out.println(" #section1 {padding-top:50px;height:auto;color: #fff; background-color: #1E88E5;}");
			out.println("#section2 {padding-top:50px;height:auto;color: #fff; background-color: rgb(38, 57, 58);}");
			out.println("#section3 {padding-top:50px;height:auto;color: #fff; background-color: rgb(205, 149, 145);}");
			out.println("section41 {padding-top:50px;height:auto;color: #fff; background-color: #00bcd4;}");
			out.println("#section42 {padding-top:50px;height:auto;color: #fff; background-color: #009688;}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body data-spy=\"scroll\" data-target=\".navbar\" data-offset=\"50\">");
			out.println("<nav class=\"navbar navbar-inverse navbar-fixed-top\">");
			out.println("<div class=\"container-fluid\">");
			out.println("<div class=\"navbar-header\">");
			out.println("<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">");
			out.println("<span class=\"icon-bar\"></span>");
			out.println("<span class=\"icon-bar\"></span>");
			out.println("<span class=\"icon-bar\"></span>  ");
			out.println("</button>");
			out.println("<a class=\"navbar-brand\" href='/Proyecto_SW1/pyet-home'><img alt=\"\" height=\"100%\" src=\"./imagenes/LogoCuadrado.jpg\"></a>");
			out.println("</div>");
			out.println("<div>");
			out.println("<div class=\"collapse navbar-collapse\" id=\"myNavbar\">");
			out.println("<ul class=\"nav navbar-nav\">");
			out.println("<li><a href=\"#section1\">PYET</a></li>");
			out.println("<li><a href=\"#section2\">Qué puedo encontrar?</a></li>");
			out.println("<li><a href=\"#section3\">Cómo surgió?</a></li>");
			out.println("<li><a href=\"#section4\">Contacto</a></li>");
			out.println("</ul>");
			out.println("<ul class=\"nav navbar-nav navbar-right\">");
			out.println("<li>");
			out.println("<a href='./formularios/registro.html'><button type=\"button\" class=\"btn btn-primary\">Crear cuenta</button></a>");
			out.println("</li>");
			out.println("<li>");
			out.println("<a href='./formularios/Login.html'><button type=\"button\" class=\"btn btn-success\">Iniciar sesión</button></a>");
			out.println("</li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</nav> ");
			out.println("<div id=\"section1\" class=\"container-fluid\">");
			out.println(" </br></br><h1 align=\"center\"><b>PYET</b></h1></br></br>");
			out.println("<p><font size=3>PYET es un lugar seguro para cualquier persona que sienta la necesidad de expresarse.</br> Somos una comunidad formada por miles de personas. </br> Nos fundamentamentos en la libertad de expresión resultante de la tolerancia y el respeto.</br> Tienes algo que decir? </br></font></p>");
			out.println(" <p align=\"center\"><font size=3><b> PYET ES TU SITIO! </b></font></p>");
			out.println("</br></br></br></br></br></br></br></br></br></br></br>");
			out.println("</br></br></br></br></br></br></br></br></br></br></br>");
			out.println("</br></br></br></br></br>");
			out.println("</div>");
			out.println("<div id=\"section2\" class=\"container-fluid\">");
			out.println("<h1 align=\"center\"><b>Qué se puede encontrar dentro de PYET?</b></h1></br></br>");
			out.println("<p><font size=3>Nuestra comunidad se basa en la publicaci�n de post dentro de la plataforma.</font></p>");
			out.println("<p><font size=3>Dentro de las diferentes tem�ticas disponibles se puede publicar el contenido deseado. Adem�s puedes consultar todos los post con la tem�tica que m�s te interese, de esta manera, solamente encontrar�s contenido de tu inter�s sin tener que perder tiempo filtrando tu mismo las distintas publicaciones. </font></p>");
			out.println("<p><font size=3> Si quieres saber m�s solo tienes que registrarte y explorar!</font></p>");
			out.println("</br></br></br></br></br></br></br></br></br></br></br>");
			out.println("</div>");
			out.println("<div id=\"section3\" class=\"container-fluid\">");
			out.println("<h1 align=\"center\"><b>Cómo surgió PYET?</b></h1></br></br>");
			out.println("<p><font size=3>PYET naci� como un proyecto de estudiantes de universidad con la motivaci�n de conseguir una plataforma de expresi�n libre y responsable enfocada en distintos temas interesantes sin tener la necesidad de perder tiempo en aquellos que no nos llamaban la atenci�n.</font></p>");
			out.println("<p><font size=3>Nos pareci� una buena idea el poder hacer participe de esta idea a todo aquel que estuviera interesado y presentara las mismas inquietudes que nosotros.</font></p>");
			out.println("<p><font size=3><b>�Todav�a no te has registrado? �A qu� estas esperando?</b></font></p>");
			out.println("</br></br></br></br></br></br></br></br></br></br></br>");
			out.println("</br></br></br></br></br></br></br></br></br></br></br>");
			out.println("</br></br></br></br></br>");
			out.println("</div>");
			
			out.println("<div id='section4' class='container-fluid bg-grey'>");
			 out.println("><br><br><br>");

	        out.println("<h2 class='text-center'>CONTACTO</h2>");

	        out.println("<div class='row'>");

	        out.println("<div class='col-sm-5'>");

	        out.println("<p>Contacta con nosotro, dicen que somos muy majos!</p>");

	        out.println("<p><span class='glyphicon glyphicon-map-marker'></span> Universidad San Pablo CEU</p>");

	        out.println("<p><span class='glyphicon glyphicon-phone'></span> +34 123456789</p>");

	        out.println("<p><span class='glyphicon glyphicon-envelope'></span> web@nos.mola</p>");

	        out.println("</div>");

	        out.println("<div class='col-sm-7 slideanim'>");

	        out.println("<div class='row'>");

	        out.println("<div class='col-sm-6 form-group'>");

	        out.println("<input class='form-control' id='name' name='name' placeholder='Name' type='text' required>");

	        out.println("</div>");

	        out.println("<div class='col-sm-6 form-group'>");

	        out.println("<input class='form-control' id='email' name='email' placeholder='Email' type='email' required>");

	        out.println("</div>");

	        out.println("</div>");

	        out.println("<textarea class='form-control' id='comments' name='comments' placeholder='Dinos cuanto te molan las Apps Web' rows='5'></textarea><br>");

	        out.println("<div class='row'>");

	        out.println("<div class='col-sm-12 form-group'>");

	        out.println("<button class='btn btn-default pull-right' type='submit'>Enviar</button>");

	        out.println("</div>");

	        out.println("</div>");

	        out.println("</div>");

	        out.println("</div>");

	        out.println("</div>");

	        out.println("<img src='./imagenes/mapsCEU.png' class='w3-image w3-greyscale-min' style='width:100%'>");

	        out.println("><br><br>");
			
			
			out.println("</body>");
			out.println("</html>");
			
		}finally {
			out.close();
		}
	}
}
