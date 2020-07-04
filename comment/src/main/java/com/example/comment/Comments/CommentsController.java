package com.example.comment.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Comments")
public class CommentsController {
	@Autowired
	private CommentsService commentsService ; 
	
	@Value("${eureka.instance.metadataMap.zone}")
	private String zone ; 
	
	@RequestMapping("/ping")
	public String ping() {
		return zone ; 
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/addComment")
	public void addCommentToArticle(@RequestBody CommentModel comment) {
		this.commentsService.addComment(comment.getCommentContent(),comment.getArticleId());
	}

	@RequestMapping(method = RequestMethod.GET , value = "/getComments/{articleId}")
	public ArticleComment getArticleComments(@PathVariable int articleId){
		ArticleComment articleComment = new ArticleComment();
		articleComment.setAllCommentForThisArticle(this.commentsService.getArticleComments(articleId));
		return articleComment;
	}
	
	@RequestMapping("/inject")
	public ResponseEntity<HttpStatus> injectData() {
		this.commentsService.injectData();
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@RequestMapping("/test")
	public CommentModel getData() {
		return new CommentModel("test ya basha",1);
	}
	
}
