package com.example.Articles;

import java.util.ArrayList;
import java.util.List;

import com.example.MQ.ModelToSend.ArticleID;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.example.ExternalModel.ArticleComment;
import com.example.ExternalModel.CommentModel;
import com.example.MQ.OrderMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service 
public class ArticleService {

	@Autowired 
	ArticleRepository articleRepo ;

	@Autowired
	OrderMessageSender orderMessageSender ;

	@Autowired
	public RestTemplate restTemplate ;
	
	
	@Autowired 
	CacheManager cacheManager ; 
	
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
	
	@CachePut("comments")
	@HystrixCommand(fallbackMethod = "commentFallBack")
	public List<CommentModel> getCommentsForArticle(int articleId) {
		ArticleComment AllComments =restTemplate.getForObject("http://Comments-Service/Comments/getComments/"+articleId, ArticleComment.class);
		List<CommentModel> ArticleComments = AllComments.getAllCommentForThisArticle();
		return ArticleComments ; 
	}
	
	public List<CommentModel> commentFallBack(int articleId){
		System.out.println("comments service break : calling fallBack comments");
		ValueWrapper valueWrapper = cacheManager.getCache("comments").get(articleId);
		if(valueWrapper != null ) {
			System.out.println("hit in cache");
			return (List<CommentModel>)valueWrapper.get();
		}
		System.out.println("no hit in cache ");
		return new ArrayList<CommentModel>() ; 
	}
	
	@CachePut("rating")
	@HystrixCommand(fallbackMethod = "ratingFallBack")
	public float getArticleRating(int articleId) {
		float Rating =restTemplate.getForObject("http://Rate-Service/Rate/getRate/"+articleId, Float.class);
		return Rating ; 
	}

	public float ratingFallBack(int articleId) {
		System.out.println("rating service break : calling ratingFallBack ");
		ValueWrapper valueWrapper = cacheManager.getCache("rating").get(articleId);
		if(valueWrapper != null ) {
			System.out.println("chached rating ");
			return (float)valueWrapper.get() ; 
		}
		System.out.println("no cached rating ");
		return 0f; 
	}
	
	
	public ArticleCommentRating parseArticleData(ArticleModel article,List<CommentModel> ArticleComments , float rating) {
		ArticleCommentRating AllInformation = new ArticleCommentRating();
		AllInformation.setArticle(article);
		AllInformation.setComment(ArticleComments);
		AllInformation.setRating(rating);
		return AllInformation ; 
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
