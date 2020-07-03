package com.example.Articles;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ExternalModel.ArticleComment;
import com.example.ExternalModel.CommentModel;





@RestController
@RequestMapping("/Articles")
public class ArticleController {
	@Autowired 
	ArticleService articleService;
	
	@Autowired

	public RestTemplate restTemplate ;
	
	/*
	@RequestMapping(method = RequestMethod.GET , value = "/addArticle")
	public ModelAndView ShowaddArticle()
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("article", new ArticleModel());
		return mav;
	}
	*/

	@RequestMapping(method = RequestMethod.POST , value = "/addArticle")
	public void addArticle(@ModelAttribute ArticleModel article)
	{
		articleService.addArticle(article);
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/Show/{id}")
	public ArticleCommentRating ShowArticle(@PathVariable int id)
	{
		ArticleModel article = this.articleService.getArticlesByID(id);
		if(article == null )
			return null ; 
		
		//get all comment for this article 
		ArticleComment AllComments =restTemplate.getForObject("http://Comments-Service/Comments/getComments/"+article.id, ArticleComment.class);
		List<CommentModel> ArticleComment = AllComments.getAllCommentForThisArticle();
		System.out.println("comment line passed --------------------------->");


		float Rating =restTemplate.getForObject("http://Rating-Service/Rate/getRate/"+article.id, Float.class);

		ArticleCommentRating AllInformation = new ArticleCommentRating();
		AllInformation.setArticle(article);
		AllInformation.setComment(ArticleComment);
		AllInformation.setRating(Rating);
		return AllInformation;
	}
	
	
	@RequestMapping(method = RequestMethod.GET , value = "/delete/{id}")
	public void deleteArticle(@PathVariable int id)
	{
		this.articleService.deleteArticle(id);
		
		//befor redirect to all article send id to comment and rating
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/all")
	public List<ArticleModel> allArticle()
	{
		List <ArticleModel> allArticle = this.articleService.GetAllArticles();
		return allArticle;
	}

	@RequestMapping(method = RequestMethod.GET , value = "/ArticleSearch/{subject}")
	public ArticleList Search(@PathVariable String subject)
	{
		List <ArticleModel> allArticle = this.articleService.GetSearchResult(subject);
		ArticleList articleList= new ArticleList();
		articleList.setArticle(allArticle);
		return articleList;
	}

	@RequestMapping(method = RequestMethod.GET , value = "/test")
	public ArticleModel test()
	{

		ArticleModel a = new ArticleModel();
		a.setSubject("test");
		a.setText("sdghdgsdfdg");
		return  a;

	}



}
