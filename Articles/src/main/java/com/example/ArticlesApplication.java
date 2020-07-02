package com.example;

import com.example.MQ.ModelToSend.ArticleID;
import com.example.MQ.OrderMessageSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
public class ArticlesApplication {
	
	
	private static OrderMessageSender orderMessageSender;


	@Autowired
	public ArticlesApplication(OrderMessageSender orderMessageSender) {
		this.orderMessageSender = orderMessageSender;
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate GetRestTemplate()
	{
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ArticlesApplication.class, args);
		ArticleID articleID=new ArticleID();
		articleID.id=0;
		orderMessageSender.sendOrderToComment(articleID);
		orderMessageSender.sendOrderToRating(articleID);
	}

}
