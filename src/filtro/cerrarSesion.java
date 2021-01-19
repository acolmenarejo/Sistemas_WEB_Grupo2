package filtro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class cerrarSesion
 */
@WebServlet(name = "cerrarSesion", urlPatterns = { "/cerrarSesion" })
public class cerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public cerrarSesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext context = req.getServletContext();
		System.out.println("cerrar sesion SERVLET");

		// Comprobamos que haya una sesion previa

		HttpSession antiguaSession = req.getSession();
		synchronized (antiguaSession) {
			if (antiguaSession != null) {
				// la anulamos
				System.out.println("invalidar sesion");
				//antiguaSession.removeAttribute("idUsuarioSesion");
				antiguaSession.setAttribute("autenticado", false);
				antiguaSession.invalidate();
				Cookie cookie = new Cookie("autologin", "0");
				resp.addCookie(cookie);
			}
			// Creamos una nueva sesion

			// redirigimos a pyet/inicio pero no funciona
			// resp.sendRedirect(req.getContextPath() + "/pyet/inicio");
			synchronized (context) {
				System.out.println("cerramos sesion");
				RequestDispatcher pyet = context.getNamedDispatcher("pyet");
				pyet.forward(req, resp);

			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
