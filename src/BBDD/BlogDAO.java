package BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

public class BlogDAO {
	private DataSource data;
	
	public BlogDAO(DataSource data) {
		this.data = data;
	}
	
	public List <Blog> list() throws SQLException{
		List<Blog> posts = new ArrayList<Blog>();
		
		try (
			Connection connection = data.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from blog");
			ResultSet result = statement.executeQuery();
			){
				while (result.next()) {
					Blog post = new Blog();
					post.setId(result.getInt("id_blog"));
					post.setNombre(result.getString("nombre"));
					post.setContenido(result.getString("contenido"));
					posts.add(post);
				}
		}
		return posts;
			
		
	}
	
}
