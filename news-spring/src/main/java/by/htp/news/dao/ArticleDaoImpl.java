package by.htp.news.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.news.bean.Article;

public class ArticleDaoImpl implements ArticleDao {
	
// add article
	@Override
	public void addArticle(Article article) {
		System.out.println("add start");
		Connection connection = null;
		PreparedStatement statement = null;
		
		String link = createText(article.getTitle(), article.getText());
		
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news?useSSL=false", "root", "1234");
		statement = connection.prepareStatement(SQLAtt.ADD_ARTICLE);
		statement.setString(1, article.getTitle());
		statement.setInt(2, 1); //status active
		statement.setString(3, article.getDate());
		statement.setString(4, article.getHeading());
		statement.setString(5, article.getAnnotation());
		statement.setString(6, link);
		
		statement.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
// get all articles
	@Override
	public Map<Integer, Article> getList() {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Map<Integer, Article> articles = new HashMap<Integer, Article>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news?useSSL=false", "root", "1234");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLAtt.GET_LIST);

			while (resultSet.next()) {
				List<String> text = getText(resultSet.getString("text"));
				
				Article article = new Article(resultSet.getInt("ID"), resultSet.getString("title"),
						resultSet.getString("date"), resultSet.getString("heading"), resultSet.getString("annotatio"),text);
						articles.put(resultSet.getInt("ID"), article);
			}
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return articles;
	}

	// get article using ID
	@Override
	public Article getArticle(int id) {

		Article article = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news?useSSL=false", "root", "1234");
			statement = connection.prepareStatement(SQLAtt.GET_ARTICLE_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			System.out.println("adding commented and article returns null");
//			while (resultSet.next()) {
//				int ID = resultSet.getInt("ID");
//				article = new Article(ID, resultSet.getString("title"), resultSet.getString("date"),
//						resultSet.getString("heading"), resultSet.getString("annotatio"), resultSet.getString("text"));
//
//			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return article;
	}

	// delete article
	@Override
	public void deleteArticle(int id) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news?useSSL=false", "root", "1234");
			statement = connection.prepareStatement("");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	// helper create txt file
	public String createText(String title, String text) {
		PrintWriter writer = null;
		String link = title + ".txt";
		String temp = "";
		int j = 0;
		try {
			writer = new PrintWriter(link, "UTF-8");
			for (int i = 0; i < text.length(); i++) {
				if (i % 120 == 0) {
					temp = text.substring(j, i);
					writer.println(temp);
					temp = "";
					j = i;
				}
			}
			temp = text.substring(j, text.length());
			writer.println(temp);
			temp="";	
			writer.close();

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		return link;

	}

	// create List of text
	public List<String> getText(String title) {
		
		Path file = Paths.get(title);
		BufferedReader reader = null;
		String line;
		List<String> array = new ArrayList<String>();
		
		if (Files.exists(file) && Files.isReadable(file)) {
			try {
				reader = Files.newBufferedReader(file, Charset.defaultCharset());
				while((line = reader.readLine()) != null) {
		        	
	            	array.add(line);		 
	        }
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return array;
	}

	
}
