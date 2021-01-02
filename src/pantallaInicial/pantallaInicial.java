package pantallaInicial;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="inicio", urlPatterns= {"/inicio"})
public class pantallaInicial extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object autenticadoObj = req.getAttribute("autenticado");
		ServletContext context = req.getServletContext();
		if(autenticadoObj != null && (boolean) autenticadoObj) {
			String nombre = req.getParameter("nombre");
			String contrasena = req.getParameter("contrasena");
			System.out.println("pantalla inicial OK");
		
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			
			try {
				
				out.println("<html>");
				out.println("<head><title>weChat</title></head>");
				out.println("<body>");
				out.println("<h1>Bienvenido a weChat</h1>");
				out.println("</body></html>");
				out.println("<img src=\"./formulario1/Paradise.jpg\"></img>");
			
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
