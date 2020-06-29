package com.example.MQ;

import com.example.MQ.ModelToSend.ArticleID;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderMessageSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrderToComment(ArticleID id) {

        this.rabbitTemplate.convertAndSend("ArticleIdToComments",id);
    }
    public void sendOrderToRating(ArticleID id) {

        this.rabbitTemplate.convertAndSend("ArticleIdToRating",id);
    }
}