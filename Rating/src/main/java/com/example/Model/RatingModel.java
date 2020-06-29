package com.example.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RatingModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Rid ; 
	
	private int rateValue ;
	
	private int articleId ;

	
	
	public RatingModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RatingModel(int rateValue, int articleId) {
		super();
		this.rateValue = rateValue;
		this.articleId = articleId;
	}


	public int getRateValue() {
		return rateValue;
	}

	public void setRateValue(int rateValue) {
		this.rateValue = rateValue;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	
	
}
