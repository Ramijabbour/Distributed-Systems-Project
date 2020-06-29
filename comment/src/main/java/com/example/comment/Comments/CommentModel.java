package com.example.comment.Comments;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CommentModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
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
