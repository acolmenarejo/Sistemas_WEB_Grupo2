package BBDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;

@WebServlet("/posts")
public class ServletPosts extends HttpServlet {
	@Resource (name="jdbc/blog")
	private DataSource data;
	private BlogDAO dao;
	
	public void init () {
		dao = new BlogDAO(data);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		try {
			List<Blog> posts = dao.list();
			request.setAttribute("posts", posts);
			request.getRequestDispatcher("/WEB_INF/posts.jsp").forward(request, response);
		}catch (SQLException e) {
			throw new ServletException("Cannot obtain products from DB");
		}
	}
}
