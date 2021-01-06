package BBDD;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="PyetServlet", urlPatterns= {"/Pyet"})
public class PyetServlet extends HttpServlet {
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
			out.println("position: relative; ");
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
			out.println("<a class=\"navbar-brand\" href=\"#\"><img alt=\"\" height=\"100%\" src=\"./imagenes/LogoCuadrado.jpg\"></a>");
			out.println("</div>");
			out.println("<div>");
			out.println("<div class=\"collapse navbar-collapse\" id=\"myNavbar\">");
			out.println("<ul class=\"nav navbar-nav\">");
			out.println("<li><a href=\"#section1\">PYET</a></li>");
			out.println("<li><a href=\"#section2\">�Qu� puedo encontrar?</a></li>");
			out.println("<li><a href=\"#section3\">�C�mo surgi�?</a></li>");
			out.println("</ul>");
			out.println("<ul class=\"nav navbar-nav navbar-right\">");
			out.println("<li>");
			out.println("<a href='./formulario1/registro.html'><button type=\"button\" class=\"btn btn-primary\">Crear cuenta</button></a>");
			out.println("</li>");
			out.println("<li>");
			out.println("<a href='./formulario1/Login.html'><button type=\"button\" class=\"btn btn-success\">Iniciar sesi�n</button></a>");
			out.println("</li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</nav> ");
			out.println("<div id=\"section1\" class=\"container-fluid\">");
			out.println(" </br></br><h1 align=\"center\"><b>PYET</b></h1></br></br>");
			out.println("<p><font size=3>PYET es un lugar seguro para cualquier persona que sienta la necesidad de expresarse.</br> Somos una comunidad formada por miles de personas. </br> Nos fundamentamentos en la libertad de expresi�n resultante de la tolerancia y el respeto.</br> �Tienes algo que decir? </br></font></p>");
			out.println(" <p align=\"center\"><font size=3><b> �PYET ES TU SITIO! </b></font></p>");
			out.println("</div>");
			out.println("<div id=\"section2\" class=\"container-fluid\">");
			out.println("<h1 align=\"center\"><b>�Qu� se puede encontrar dentro de PYET?</b></h1></br></br>");
			out.println("<p><font size=3>Nuestra comunidad se basa en la publicaci�n de post dentro de la plataforma.</font></p>");
			out.println("<p><font size=3>Dentro de las diferentes tem�ticas disponibles se puede publicar el contenido deseado. Adem�s puedes consultar todos los post con la tem�tica que m�s te interese, de esta manera, solamente encontrar�s contenido de tu inter�s sin tener que perder tiempo filtrando tu mismo las distintas publicaciones. </font></p>");
			out.println("<p><font size=3> Si quieres saber m�s solo tienes que registrarte y explorar!</font></p>");
			out.println("</div>");
			out.println("<div id=\"section3\" class=\"container-fluid\">");
			out.println("<h1 align=\"center\"><b>�C�mo surgi� PYET?</b></h1></br></br>");
			out.println("<p><font size=3>PYET naci� como un proyecto de estudiantes de universidad con la motivaci�n de conseguir una plataforma de expresi�n libre y responsable enfocada en distintos temas interesantes sin tener la necesidad de perder tiempo en aquellos que no nos llamaban la atenci�n.</font></p>");
			out.println("<p><font size=3>Nos pareci� una buena idea el poder hacer participe de esta idea a todo aquel que estuviera interesado y presentara las mismas inquietudes que nosotros.</font></p>");
			out.println("<p><font size=3><b>�Todav�a no te has registrado? �A qu� estas esperando?</b></font></p>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			
		}finally {
			out.close();
		}
	}
}
