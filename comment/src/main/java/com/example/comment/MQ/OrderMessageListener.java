package com.example.comment.MQ;
import com.example.comment.MQ.ModelToRecv.ArticleID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderMessageListener {


    static final Logger logger = LoggerFactory.getLogger(OrderMessageListener.class);

   @RabbitListener(queues = "ArticleIdToComments")
    public void processOrder(ArticleID articleID) {
        System.out.println("Article ID recieved with info : " + articleID.id);
        logger.info("Order Received: " + articleID);
    }
}

