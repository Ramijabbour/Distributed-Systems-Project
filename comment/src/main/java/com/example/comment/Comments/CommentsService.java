package com.example.comment.Comments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

	@Autowired
	private CommentRepository commentsRepostory ; 
	
	public void addComment(String content , int articleId ) {
		CommentModel commentModel = new CommentModel(content,articleId) ; 
		this.commentsRepostory.save(commentModel); 
	}
	
	public void deleteArticleComments(int articleId ) {
		List<CommentModel> commentList = this.commentsRepostory.findByarticleId(articleId);
		if(commentList == null ) {
			return ; 
		}
		for(CommentModel comment : commentList ) {
			this.commentsRepostory.delete(comment);
		}
		
	}
	
	public List<CommentModel> getArticleComments(int articleId ){
		List<CommentModel> commentList = this.commentsRepostory.findByarticleId(articleId);
		if(commentList == null ) {
			return new ArrayList<CommentModel>(); 
		}
		return commentList; 
	}
	
	public void injectData() {
		for(int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				CommentModel cm = new CommentModel("content "+j, i);
				this.commentsRepostory.save(cm);
			}
		}
	}
	
	
}
