package pantallaInicial;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class inicializadorVariables
 */
@WebServlet("/inicializadorVariables")
public class inicializadorVariables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection connection = null;
	private Statement statement = null;
	ResultSet resultSet = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inicializadorVariables() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() throws ServletException {
		super.init();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2", "root", "qwertyuiop1234567890");
			statement = connection.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("connected");
			statement.execute("CREATE TABLE IF NOT EXISTS usuario (\r\n"
					+ " id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\r\n"
					+ " nombreusuario VARCHAR (20) NOT NULL,\r\n"
					+ " contrasena VARCHAR(20) NOT NULL,\r\n"
					+ " correo VARCHAR (100) NOT NULL UNIQUE)");
			
			statement.execute("CREATE TABLE IF NOT EXISTS post (\r\n"
					+ "    id_post INT NOT NULL AUTO_INCREMENT,\r\n"
					+ "    id_usuario INT NOT NULL,\r\n"
					+ "    contenido TEXT NOT NULL,\r\n"
					+ "    PRIMARY KEY(id_post),\r\n"
					+ "    INDEX(id),\r\n"
					+ "    FOREIGN KEY (id) REFERENCES usuario (id)\r\n"
					+ "        ON DELETE CASCADE\r\n"
					+ "        ON UPDATE NO ACTION\r\n"
					+ ")");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String identificadorSesion = session.getId();
	
		//Escribir dispacher a Pyet.html
		//Usar identificadorSesion �para la base de datos? �para asociarlo a un post?
		
		//Esto a modo de visualizaci�n. Luego lo habr� que complementarlo con la estructura de Pyet.html
		resp.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = resp.getWriter()){
			out.println("<html>");
			out.println("<head><title>Visualizador Variables</title></head>");
			out.println("<body>");
			out.println("<h1>Servlet ContadorSesion en " + req.getContextPath()+ "</h1>");
			out.println("<h2>Su id es " + session.getId() + "</h2>");
			out.println("</body></html>");
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
