package com.example.Rate.Rates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RateService {


	@Autowired
	RateRepository ratingRepository ;
	
	public void addRate(int rateValue , int articleId)
	{
		RateModel ratingModle = new RateModel(rateValue , articleId);
		this.ratingRepository.save(ratingModle);
	}
	
	public void deleteArticleRating(int articleId)
	{
		
		List<RateModel> RatingList = this.ratingRepository.findByarticleId(articleId);
		if(RatingList.size() == 0)
		{
			return ; 
		}
		for(RateModel rate : RatingList)
		{
			this.ratingRepository.delete(rate);
		}
	}
	
	
	public void injectData() {
		for(int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				RateModel rm = new RateModel(j, i);
				this.ratingRepository.save(rm);
			}
		}
	}
	
	
	
	public float avgRateForArticle(int articleId)
	{
		List<RateModel> RatingList = this.ratingRepository.findByarticleId(articleId);
		if(RatingList.size() == 0)
		{
			return 0 ; 
		}
		int sum = 0; 
		for(RateModel rate : RatingList)
		{
			sum += rate.getRateValue(); 
		}
		return sum/RatingList.size();
	}
	
	
}
