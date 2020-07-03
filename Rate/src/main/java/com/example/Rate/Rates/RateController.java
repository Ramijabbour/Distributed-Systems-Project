package com.example.Rate.Rates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/Rate")
public class RateController {
	@Autowired
	RateService ratingService ;
	
	@RequestMapping(method = RequestMethod.POST ,value = "/addRate")
	public void addRating (@RequestBody RateModel ratingModel )
	{
		this.ratingService.addRate(ratingModel.getRateValue(), ratingModel.getArticleId());
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/getRate/{articleId}")
	public float getRatingToArticle(@PathVariable int articleId)
	{
		 return this.ratingService.avgRateForArticle(articleId);
	} 
	
}
