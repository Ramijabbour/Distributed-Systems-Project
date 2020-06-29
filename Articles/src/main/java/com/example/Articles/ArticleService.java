package com.example.Articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service 
public class ArticleService {

	@Autowired 
	ArticleRepository articleRepo ; 
	
	public void addArticle (ArticleModel a)
	{
		 this.articleRepo.save(a);
	}
	
	public List<ArticleModel> GetAllArticles()
	{
		return articleRepo.findAll();
	}
	
	public ArticleModel getArticlesByID(int id)
	{
		List<ArticleModel> allArticles =articleRepo.findAll();
		if(allArticles.isEmpty()) {
			System.out.println("empty articlesList");
			return null;}
		
		for(ArticleModel article : allArticles)
			if(article.getId() == id)
				return article;
		
		System.out.println("requested article not found ");
		return null ; 			
	}
	
	public void deleteArticle(int id )
	{
		articleRepo.deleteById(id);
	}
	
	
}
