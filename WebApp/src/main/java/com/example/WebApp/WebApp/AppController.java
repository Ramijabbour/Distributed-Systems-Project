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
import com.example.WebApp.WebApp.Models.CommentModel;
import com.example.WebApp.WebApp.Models.RateModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.example.WebApp.WebApp.Models.ArticleCommentRating;

@RestController
@RequestMapping("/wiki")
public class AppController {

	@Autowired
	public RestTemplate restTemplate ;

	
	private String gateWay = "localhost"; 

	@RequestMapping(method = RequestMethod.GET , value ="/home")
	public ModelAndView getHomePage() {
		ModelAndView mav = new ModelAndView("Home");
		return mav  ;
	}


	
	@RequestMapping(method = RequestMethod.GET , value = "/getArticle/{articleId}")
		public ModelAndView getArticle(@PathVariable int articleId) {	
		System.out.println("http://"+gateWay+":8085/api/Articles/Articles/Show/");
		ArticleCommentRating articleModel = restTemplate.getForObject("http://"+gateWay+":8085/api/Articles/Articles/Show/"+articleId, ArticleCommentRating.class);		
		ModelAndView mav = new ModelAndView("viewArticle");
		mav.addObject("article", articleModel.getArticle());
		mav.addObject("commentsList", articleModel.getComment());
		mav.addObject("rating", articleModel.getRating());
		mav.addObject("comment", new CommentModel());
		mav.addObject("rate", new RateModel());
		return mav ; 
	}
	


	@RequestMapping(method = RequestMethod.GET , value ="/all")
	public ModelAndView getAllArticlePage() {
		ModelAndView mav = new ModelAndView("AllArticles");
		ArticleList AllArticles = restTemplate.getForObject("http://"+gateWay+":8085/api/Articles/Articles/all",ArticleList.class);
		List <ArticleModel> articles = AllArticles.getArticle();
		mav.addObject("articles",articles);
		return mav  ;
	}
	
	
	@RequestMapping(method = RequestMethod.POST , value ="/search")
	public ModelAndView getArticleSearchPage(@RequestParam("search") String title) {
		ModelAndView mav = new ModelAndView("ArticlesSearch");
		ArticleList AllArticles = restTemplate.getForObject("http://"+gateWay+":8085/api/Search/Search/"+title,ArticleList.class);
		
		List<ArticleModel> articles = AllArticles.getArticle();
		mav.addObject("Allarticles",articles);
		return mav  ;
	}
	
	
	


	@RequestMapping(method = RequestMethod.POST , value = "/addComment/{articleId}")
	public void addCommentToArticle(@PathVariable int articleId,@ModelAttribute CommentModel commentModel,HttpServletResponse response)throws IOException {
		commentModel.setArticleId(articleId);
		
		String url = "http://"+gateWay+":8085/api/Comments/Comments/addComment";
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
		String url = "http://"+gateWay+":8085/api/Rate/Rate/addRate";
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
		String url = "http://"+gateWay+":8085/api/Articles/Articles/addArticle";
		JSONObject request = new JSONObject();
		request.put("subject", articelModel.getSubject());
		request.put("text", articelModel.getText());
		request.put("category", articelModel.getCategory());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		restTemplate
		  .exchange(url, HttpMethod.POST, entity, String.class);
		response.sendRedirect("/wiki/all");
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/deleteArticle/{articleId}")
	public void deleteArticle(@PathVariable int articleId , HttpServletResponse response )throws IOException {
		restTemplate.getForObject("http://"+gateWay+":8085/api/Articles/Articles/delete/"+articleId, String.class);
		response.sendRedirect("/wiki/all");
	}
	
	
	@RequestMapping(method = RequestMethod.GET , value = "/related/{category}")
	public ModelAndView getRelatedArticles(@PathVariable String category) {
		ModelAndView mav = new ModelAndView("AllArticles");
		ArticleList AllArticles = restTemplate.getForObject("http://"+gateWay+":8085/api/Related/RelatedArticle/"+category,ArticleList.class);
		List <ArticleModel> articles = AllArticles.getArticle();
		mav.addObject("articles",articles);
		return mav  ;
		
	}
	
}
