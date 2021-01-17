package filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * Servlet Filter implementation class autologinFilter
 */
@WebFilter(filterName = "autologinFilter", urlPatterns = { "/pyet/*"})
public class autologinFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("[Filtro autologin] ");
	}

	/**
	 * Default constructor.
	 */
	public autologinFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// Comprobamos la existencia de la cookie. Si existe logueamos directamente al
		// usuario. Si no existe creamos la cookie

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		Cookie[] cookies = req.getCookies();
		HttpSession session = req.getSession();

		ServletContext context = req.getServletContext();

		// Comprobamos si ya se comprob� la cookie
		System.out.println("[Filtro autologin] Inicia filtro");

		Object autenticadoObj = session.getAttribute("autenticado");

		if (autenticadoObj == null) {
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					System.out.println("Filtro autoLogin - iterador cookies");
					if (cookies[i].getName().equals("autologin")) {

						System.out.println(cookies[i]);
						int idUsuarioAutoLogin = Integer.valueOf(cookies[i].getValue());

						session.setAttribute("autenticado", true);
						session.setAttribute("idUsuarioSesion", idUsuarioAutoLogin);
						System.out.println("Filtro autoLogin - cookie existe y procesada");
						chain.doFilter(request, response);
						break;
					}else {
						System.out.println("[Filtro autologin] else");
						RequestDispatcher pyet = context.getNamedDispatcher("pyet");
						pyet.forward(request, response);

					} 
				}
			} else {
				System.out.println("[Filtro autologin] else");
				RequestDispatcher pyet = context.getNamedDispatcher("pyet");
				pyet.forward(request, response);

			}
		} else {
			System.out.println("[Filtro autologin] fin, ya ha iniciado sesion, su id es"
					+ session.getAttribute("idUsuarioSesion"));
			chain.doFilter(request, response);
		}
	
	}

}
