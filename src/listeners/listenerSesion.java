package listeners;

import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class listenerSesion implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("[Listener Sesi�n] A las " + getTime() + "  se cre� la sesion con ID: "
                + session.getId() + " MaxInactiveInterval="
                + session.getMaxInactiveInterval());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("[Listener Sesi�n] A las " + getTime() + " se destruy� la sesion con ID: "
                + session.getId());
    }

    private String getTime() {
        return new Date(System.currentTimeMillis()).toString();
    }
}