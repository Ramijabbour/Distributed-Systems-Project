package com.example.WebApp.WebApp;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.WebApp.WebApp.Models.ArticleCommentRating;
import com.example.WebApp.WebApp.Models.ArticleModel;
import com.example.WebApp.WebApp.Models.CommentModel;
import com.example.WebApp.WebApp.Models.RateModel;

@RestController
@RequestMapping("/wiki")
public class AppController {


	@Autowired
	public RestTemplate restTemplate ;
	
	@RequestMapping(method = RequestMethod.GET , value = "/getArticle/{articleId}")
	public ModelAndView getArticle(@PathVariable int articleId) {	
		ArticleCommentRating articleModel = restTemplate.getForObject("http://localhost:8085/api/Articles/Articles/Show/"+articleId, ArticleCommentRating.class);
		ModelAndView mav = new ModelAndView("viewArticle");
		mav.addObject("article", articleModel.getArticle());
		mav.addObject("commentsList", articleModel.getComment());
		mav.addObject("rating", articleModel.getRating());
		mav.addObject("comment", new CommentModel());
		mav.addObject("rate", new RateModel());
		return mav ; 
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/addComment/{articleId}")
	public void addCommentToArticle(@PathVariable int articleId,@ModelAttribute CommentModel commentModel,HttpServletResponse response)throws IOException {
		commentModel.setArticleId(articleId);
		
		String url = "http://localhost:8085/api/Comments/Comments/addComment";
		JSONObject request = new JSONObject();
		request.put("articleId", articleId);
		request.put("commentContent", commentModel.getCommentContent());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		restTemplate
		  .exchange(url, HttpMethod.POST, entity, String.class);
		response.sendRedirect("/wiki/getArticle/"+articleId);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/addRate/{articleId}")
	public void addRateToArticle(@PathVariable int articleId,@ModelAttribute RateModel rateModel ,HttpServletResponse response) throws IOException {
		rateModel.setArticleId(articleId);
		String url = "http://localhost:8085/api/Rating/Rate/addRate";
		JSONObject request = new JSONObject();
		request.put("articleId", articleId);
		request.put("rateValue", rateModel.getRateValue());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		restTemplate
		  .exchange(url, HttpMethod.POST, entity, String.class);
		response.sendRedirect("/wiki/getArticle/"+articleId);
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/addArticle")
	public ModelAndView addArticle() {
		ModelAndView mav = new ModelAndView("addArticle");
		mav.addObject("article", new ArticleModel());
		return mav ; 
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/addArticle")
	public void postArticle(@ModelAttribute ArticleModel articelModel , HttpServletResponse response )throws IOException  {
		String url = "http://localhost:8085/api/Articles/Articles/addArticle";
		JSONObject request = new JSONObject();
		request.put("subject", articelModel.getSubject());
		request.put("text", articelModel.getText());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		restTemplate
		  .exchange(url, HttpMethod.POST, entity, String.class);
		response.sendRedirect("/wiki/addArticle");
	}
	
	
}
