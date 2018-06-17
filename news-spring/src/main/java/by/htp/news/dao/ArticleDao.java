package by.htp.news.dao;


import java.util.Map;

import by.htp.news.bean.Article;

public interface ArticleDao {
	
	public Map<Integer, Article> getList();
	
	public Article getArticle(int id);
	
	public void deleteArticle(int id);
	
	public void addArticle(Article article);
	
	

}
