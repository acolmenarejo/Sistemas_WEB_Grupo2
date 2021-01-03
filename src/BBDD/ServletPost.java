package BBDD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="posts", urlPatterns= {"/posts"})
public class ServletPost extends HttpServlet{
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet rs;
	private List <Blog> posts = new ArrayList<Blog>();
	
	public void init() throws ServletException {
		super.init();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog", "root", "root");
			statement = connection.createStatement();
			 rs = statement.executeQuery("SELECT * from blog");
			 while (rs.next()) {
					Blog post = new Blog();
					post.setId(rs.getInt("id_blog"));
					post.setNombre(rs.getString("titulo"));
					post.setContenido(rs.getString("contenido"));
					posts.add(post);
					System.out.println(posts);
				}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
	}
}
