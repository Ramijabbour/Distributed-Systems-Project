package com.example.WebApp.WebApp.Models;

public class ArticleModel {
	int id ;
	String subject ; 
	String brief;
	String text ;
	
	
	public ArticleModel() {
		
	}
	
	public ArticleModel(String subject,String brief, String text) {
		super();
		this.subject = subject;
		this.brief=brief;
		this.text = text;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	} 
}
