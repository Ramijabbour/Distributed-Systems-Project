package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.RatingModel;
import com.example.Repository.RatingRepository;


@Service
public class RatingService {

	@Autowired
	RatingRepository ratingRepository ;
	
	public void addRate(int rateValue , int articleId)
	{
		RatingModel ratingmodle = new RatingModel(rateValue , articleId);
		this.ratingRepository.save(ratingmodle);
	}
	
	public void deleteArticleRating(int articleId)
	{
		
		List<RatingModel> RatingList = this.ratingRepository.findByarticleId(articleId);
		if(RatingList == null)
		{
			return ; 
		}
		for(RatingModel rate : RatingList)
		{
			this.ratingRepository.delete(rate);
		}
	}
	
	
	public float avgRateForArticle(int articleId)
	{
		List<RatingModel> RatingList = this.ratingRepository.findByarticleId(articleId);
		if(RatingList == null)
		{
			return 0 ; 
		}
		int sum = 0; 
		for(RatingModel rate : RatingList)
		{
			sum += rate.getRateValue(); 
		}
		return sum/RatingList.size();
	}
	
	
	
	
}
