package com.example.WebApp.WebApp.Models;

public class RateModel {

	private int Rid ; 
	
	private int rateValue ;
	
	private int articleId ;

	
	
	public RateModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RateModel(int rateValue, int articleId) {
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
