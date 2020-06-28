package com.example.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Articles")
public class articles {

	@Id
	int id ;
	String subject ; 
	String text ;
	
	
	public articles() {
		
	}
	
	public articles(String subject, String text) {
		super();
		this.subject = subject;
		this.text = text;
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
	
	
	
}
