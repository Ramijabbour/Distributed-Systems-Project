package com.example.Articles;

import java.util.ArrayList;
import java.util.List;

import com.example.MQ.ModelToSend.ArticleID;
import com.example.MQ.OrderMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service 
public class ArticleService {

	@Autowired 
	ArticleRepository articleRepo ;

	@Autowired
	OrderMessageSender orderMessageSender ;

	
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
		ArticleID articleID=new ArticleID();
		articleID.id=id;
		articleRepo.deleteById(id);
		orderMessageSender.sendOrderToComment(articleID);
		orderMessageSender.sendOrderToRating(articleID);

	}


	/*public List<ArticleModel> GetSearchResult(String name) {
		List<ArticleModel> all = articleRepo.findAll();
		ArrayList<ArticleModel> result = new ArrayList<ArticleModel>();

		for(ArticleModel article: all )
		{
			if(article.getSubject().equalsIgnoreCase(name))
				result.add(article);
		}
		return result;

	}*/
}
