package by.htp.news.dao;

public class SQLAtt {
	
	public final static String ADD_ARTICLE = "INSERT INTO news.news (title, status, date, heading, annotatio, text) VALUES (?,?,?,?,?,?)";
	public final static String GET_LIST = "SELECT * FROM news.news WHERE status = 1";
	public final static String GET_ARTICLE_BY_ID = "SELECT * FROM news.news WHERE id = ?";
	public final static String DELETE_ARTICLE_BY_ID = "UPDATE news.news SET status = 0 WHERE id = ?";

}
