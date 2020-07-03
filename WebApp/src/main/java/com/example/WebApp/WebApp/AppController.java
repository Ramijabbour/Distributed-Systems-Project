package com.example.WebApp.WebApp;

import com.example.WebApp.WebApp.Models.ArticleList;
import com.example.WebApp.WebApp.Models.ArticleModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.WebApp.WebApp.Models.ArticleCommentRating;

@RestController
@RequestMapping("/wiki")
public class AppController {


	@Autowired
	public RestTemplate restTemplate ;
	

	@RequestMapping(method = RequestMethod.GET , value ="/home")
	public ModelAndView getHomePage() {
		ModelAndView mav = new ModelAndView("addArticle");
		return mav  ;
	}


	
	@RequestMapping(method = RequestMethod.GET , value = "/getArticle/{articleId}")
	public ArticleCommentRating getArticle(@PathVariable int articleId) {
		System.out.println("invoke------------------------------>");
		ArticleCommentRating articleModel = restTemplate.getForObject("http://localhost:8085/api/Articles/Articles/Show/"+articleId, ArticleCommentRating.class);
		System.out.println("return ------------------------------>");
		return articleModel ; 
	}


	@RequestMapping(method = RequestMethod.GET , value ="/all")
	public ModelAndView getAllArticlePage() {
		ModelAndView mav = new ModelAndView("AllArticles");
		ArticleList AllArticles = restTemplate.getForObject("http://localhost:8085/api/Articles/Articles/all",ArticleList.class);
		List <ArticleModel> articles = AllArticles.getArticle();
		mav.addObject("articles",articles);
		return mav  ;
	}
	
	
	@RequestMapping(method = RequestMethod.POST , value ="/search")
	public ModelAndView getArticleSearchPage(@RequestParam("search") String title) {
		ModelAndView mav = new ModelAndView("ArticlesSearch");
		ArticleList AllArticles = restTemplate.getForObject("http://localhost:8085/api/Search/Search/"+title,ArticleList.class);
		
		List<ArticleModel> articles = AllArticles.getArticle();
		mav.addObject("Allarticles",articles);
		return mav  ;
	}
	
	
	
}
