package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class SearchService {

	 @Autowired
	 public RestTemplate restTemplate;

	@Autowired 
	CacheManager cacheManager ; 
	 
	
	@CachePut("articles")
	@HystrixCommand(fallbackMethod = "searchFallBack")
	public ArticleList searchForArticles(String subject) {
		ArticleList article = restTemplate.getForObject("http://Articles-Service/Articles/all/", ArticleList.class);
		if (article == null)
            return null;
        else
        {
            List<ArticleModel> all = article.getArticle();
            ArrayList<ArticleModel> result = new ArrayList<ArticleModel>();

            for(ArticleModel a: all )
            {
                if(a.getSubject().equalsIgnoreCase(subject))
                    result.add(a);
            }
            ArticleList newArticle = new ArticleList();
            newArticle.setArticle(result);
            return newArticle;
        }
	}
	
	public ArticleList searchFallBack(String subject) {
		System.out.println("search fallBack : articles instance not available");
		System.out.println("------------------------------------------------------------------------------");
		ValueWrapper valueWrapper = cacheManager.getCache("articles").get(subject);
		if(valueWrapper != null ) {
			System.out.println("chached articles ");
			return (ArticleList)valueWrapper.get() ; 
		}
		System.out.println("no cached articles ");
		return new ArticleList(); 
	}
	
}
