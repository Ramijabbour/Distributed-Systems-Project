package com.example.ExternalModel;

import java.util.List;

public class ArticleComment {

	public List<CommentModel> AllCommentForThisArticle;

	
	public List<CommentModel> getAllCommentForThisArticle() {
		return AllCommentForThisArticle;
	}

	public void setAllCommentForThisArticle(List<CommentModel> allCommentForThisArticle) {
		AllCommentForThisArticle = allCommentForThisArticle;
	}
	
	
	
}
