package by.htp.news.service;


import java.util.List;
import java.util.Map;

import by.htp.news.bean.Article;
import by.htp.news.dao.ArticleDao;


public class ArticleServiceImpl implements ArticleService{
	
	private ArticleDao articleDao;
	
	
	public ArticleServiceImpl(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public Map<Integer, Article> getList() {
		return articleDao.getList();
	}

	@Override
	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

	@Override
	public void addArticle(Article article) {
		articleDao.addArticle(article);
		
	}
	
	@Override
	public void readArticleByID(int id, Map<Integer,Article> articles) {
		List<String> array = articles.get(id).getTextList();
		for(int i = 0; i < array.size(); i++) {
        	System.out.println(array.get(i));
        }
		
		
	}

	

	
	
	
	

}
