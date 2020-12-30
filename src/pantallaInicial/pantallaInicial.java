package pantallaInicial;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="inicio", urlPatterns= {"/inicio"})
public class pantallaInicial extends HttpServlet{

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String contraseña = req.getParameter("contrase�a");
		String edad = req.getParameter("edad");
		String[] hobbies = req.getParameterValues("hobbies");
		System.out.println(email);
	}

	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
		
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
		
		
		
	}
	
	
	
	
	
}
