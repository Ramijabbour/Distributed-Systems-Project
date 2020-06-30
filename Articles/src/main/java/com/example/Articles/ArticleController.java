package com.example.Articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.ExternalModel.ArticleComment;
import com.example.ExternalModel.CommentModel;
import com.example.ExternalModel.RatingModel;





@RestController
@RequestMapping("/Articles")
public class ArticleController {
	@Autowired 
	ArticleService articleService;
	
	@Autowired
	public RestTemplate restTemplate ;
	
	
	@RequestMapping(method = RequestMethod.GET , value = "/addArticle")
	public ModelAndView ShowaddArticle()
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("article", new ArticleModel());
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/addArticle")
	public void addArticle(@ModelAttribute ArticleModel article)
	{
		 articleService.addArticle(article);
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/Show/{id}")
	public ModelAndView ShowArticle(@PathVariable int id)
	{
		ModelAndView mav = new ModelAndView();
		ArticleModel article = this.articleService.getArticlesByID(id);
		
		//get all comment for this article 
		ArticleComment AllComments =restTemplate.getForObject("http://localhost:8089/Comments/getComments/"+article.id, ArticleComment.class);
		List<CommentModel> ArticleComment = AllComments.getAllCommentForThisArticle();
		
		//get rate for this article
		
		
		float Rating =restTemplate.getForObject("http://localhost:8083/getRate/"+article.id, Float.class);
		mav.addObject("article", article);
		mav.addObject("ArticleComment", ArticleComment);
		mav.addObject("rating", Rating);

		
		return mav;
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET , value = "/delete/{id}")
	public void deleteArticle(@PathVariable int id)
	{
		this.articleService.deleteArticle(id);
		
		//befor redirect to all article send id to comment and rating
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/all")
	public ModelAndView allArticle()
	{
		ModelAndView mav = new ModelAndView();
		List <ArticleModel> allArticle = this.articleService.GetAllArticles();
		mav.addObject("articles", allArticle);
		return mav;
	}
	
	
	
	
	
	
}
