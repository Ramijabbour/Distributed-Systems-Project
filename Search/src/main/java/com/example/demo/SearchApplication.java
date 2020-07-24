package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableCaching
public class SearchApplication {


	@Bean
	@LoadBalanced
	public RestTemplate GetRestTemplate()
	{
		return new RestTemplate();
	}

	
	@Bean
	public CacheManager chacheManager() {
		return new ConcurrentMapCacheManager("articles");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

}
