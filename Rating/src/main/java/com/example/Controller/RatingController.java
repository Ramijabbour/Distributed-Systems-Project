package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.RatingModel;
import com.example.Service.RatingService;


@RestController
@RequestMapping("/Rating")
public class RatingController {

	@Autowired
	RatingService ratingService ;
	
	@RequestMapping(method = RequestMethod.POST ,value = "/addRate")
	public void addRating (@ModelAttribute RatingModel ratingModel )
	{
		this.ratingService.addRate(ratingModel.getRateValue(), ratingModel.getArticleId());
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/getRate/{atricleId}")
	public float getRatingToArticle(@PathVariable int articleId)
	{
		 return this.ratingService.avgRateForArticle(articleId);
	}
	
	
}
