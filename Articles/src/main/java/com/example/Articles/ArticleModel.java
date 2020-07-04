package com.example.Articles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Articles")
public class ArticleModel  {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	int id ;
	String subject ;
	String brief;
	@Column(columnDefinition = "varchar(5000)")
	String text ;
	public ArticleModel() {
		
	}
	
	public ArticleModel(String subject, String brief,String text) {
		super();
		this.subject = subject;
		this.text = text;
		this.brief=brief;
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
