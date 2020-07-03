package com.example.WebApp.WebApp;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.WebApp.WebApp.Models.ArticleCommentRating;

@RestController
@RequestMapping("/wiki")
public class AppController {

	
	RestTemplate restTemplate ; 
	
	@RequestMapping(method = RequestMethod.GET , value ="/home")
	public ModelAndView getHomePage() {
		ModelAndView mav = new ModelAndView("addArticle");
		return mav  ;
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/getArticle/{articleId}")
	public ArticleCommentRating getArticle(@PathVariable int articleId) {
		System.out.println("invoke------------------------------>");
		ArticleCommentRating articleModel = restTemplate.getForObject("http://localhost:9999/Articles/Show/"+articleId, ArticleCommentRating.class);
		System.out.println("return ------------------------------>");
		return articleModel ; 
	}
}
