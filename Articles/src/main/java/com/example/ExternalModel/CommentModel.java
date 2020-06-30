package com.example.ExternalModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class CommentModel {

    private int id;
	
	private String commentContent ; 
	
	private int articleId ;

	public CommentModel() {}
	
	public CommentModel(String commentContent, int articleId) {
		super();
		this.commentContent = commentContent;
		this.articleId = articleId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	} 	
	
}
