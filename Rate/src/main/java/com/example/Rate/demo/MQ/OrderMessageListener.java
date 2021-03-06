package com.example.Rate.demo.MQ;


import com.example.Rate.Rates.RateService;
import com.example.demo.Rate.MQ.ModelToRecv.ArticleID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMessageListener {


    static final Logger logger = LoggerFactory.getLogger(OrderMessageListener.class);

    @Autowired
    RateService ratingService ;
    
    @RabbitListener(queues = "ArticleIdToRating")
    public void processOrder(ArticleID articleID) {
    	
 	   System.out.println("request to delete Rate of article #"+articleID.getId());	   
        this.ratingService.deleteArticleRating(articleID.getId());
 	   System.out.println("delete operation done !");

        logger.info("Order Received: " + articleID);
    }
}


