package com.example.Articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;





@RestController
@RequestMapping("/Articles")
public class ArticleController {
	@Autowired 
	ArticleService articleService;
	
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
		
		mav.addObject("article", article);
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
