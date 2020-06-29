package com.example.comment.Comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Comments")
public class CommentsController {
	@Autowired
	private CommentsService commentsService ; 
	
	@RequestMapping(method = RequestMethod.POST , value = "/addComment")
	public void addCommentToArticle(@ModelAttribute CommentModel comment) {
		this.commentsService.addComment(comment.getCommentContent(),comment.getArticleId());
	}

	@RequestMapping(method = RequestMethod.GET , value = "/getComments/{articleId}")
	public List<CommentModel> getArticleComments(@PathVariable int articleId){
		return this.commentsService.getArticleComments(articleId);
	}
	
	@GetMapping("/inject")
	public ResponseEntity<HttpStatus> injectData() {
		this.commentsService.injectData();
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
}
