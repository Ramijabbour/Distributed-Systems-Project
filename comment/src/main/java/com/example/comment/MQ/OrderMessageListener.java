package com.example.comment.MQ;
import com.example.comment.Comments.CommentsService;
import com.example.comment.MQ.ModelToRecv.ArticleID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMessageListener {


    static final Logger logger = LoggerFactory.getLogger(OrderMessageListener.class);

    @Autowired 
    private CommentsService commentsService ; 
    
   @RabbitListener(queues = "ArticleIdToComments")
    public void processOrder(ArticleID articleID) {
	   System.out.println("request to delete Comments of article #"+articleID.getId());	
	   this.commentsService.deleteArticleComments(articleID.getId()); 
	   System.out.println("delete operation done !");
       logger.info("Order Received: " + articleID);
    }
}

