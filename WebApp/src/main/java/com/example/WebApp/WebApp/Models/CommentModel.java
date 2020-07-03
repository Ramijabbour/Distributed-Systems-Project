package com.example.WebApp.WebApp.Models;

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
