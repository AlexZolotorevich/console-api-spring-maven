package by.htp.news.service;


import java.util.Map;


import by.htp.news.bean.Article;

public interface ArticleService {
	
	public Map<Integer, Article> getList();
	
	public void deleteArticle(int id);
	
	public void addArticle(Article article);
	
	public void readArticleByID(int id, Map<Integer,Article> articles);

}
