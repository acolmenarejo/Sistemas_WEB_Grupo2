package BBDD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class tematica */
@WebServlet("/tematica")
public class tematica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object autenticadoObj = req.getAttribute("autenticado");
		Object temaObj = req.getAttribute("tema");
		ServletContext context = req.getServletContext();
		if(temaObj != null && autenticadoObj != null && (boolean) autenticadoObj) {
			Connection connection = null;
			Statement statement = null;
			ResultSet rs;
			
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			
			//Impresión del html:
			//HACER: idem pantallaInicial
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "qwertyuiop1234567890");
				statement = connection.createStatement();
				rs = statement.executeQuery("SELECT * from post WHERE tema='"+ temaObj.toString() +"'LIMIT 20");
				
				out.println("<div class='container container-fluid' style='margin-top:80px'>");
				//Iterador para imprimir los posts
				while (rs.next()) {
					//HACER:
					//Copiar lo de pantallaInicial cuando esté bien el comillado
					out.println("<div class='car'>");
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
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				out.close();
			}
			
			out.println("</body></html>");
			
		
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
