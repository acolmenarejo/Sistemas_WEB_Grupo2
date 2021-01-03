package listeners;

import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class listenerSesion implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("[Listener Sesión] A las " + getTime() + "  se creó la sesion con ID: "
                + session.getId() + " MaxInactiveInterval="
                + session.getMaxInactiveInterval());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("[Listener Sesión] A las " + getTime() + " se destruyó la sesion con ID: "
                + session.getId());
    }

    private String getTime() {
        return new Date(System.currentTimeMillis()).toString();
    }
}