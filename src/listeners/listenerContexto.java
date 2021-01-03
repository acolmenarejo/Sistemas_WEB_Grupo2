package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class listenerContexto implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();
        System.out.println("[Listener Contexto] Aplicacion arrancando en el contexto "
                + contexto.getContextPath()
                + " con el siguiente parámetro de configuracion: "
                + contexto.getInitParameter("parametro"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();
        System.out.println("[Listener Contexto] Aplicacion del contexto "
                + contexto.getContextPath() + " deteniéndose.");
    }
}
