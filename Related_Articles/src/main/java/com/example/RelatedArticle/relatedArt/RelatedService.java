package com.example.RelatedArticle.relatedArt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.RelatedArticle.Article.ArticleList;
import com.example.RelatedArticle.Article.ArticleModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service 
public class RelatedService {
	@Autowired
    public RestTemplate restTemplate;
	
	@Autowired 
	CacheManager cacheManager ; 
	 
	@CachePut("articles")
	@HystrixCommand(fallbackMethod = "relatedFallBack")
	public ArticleList getRelatedArticles(String Category) {
		ArticleList article = restTemplate.getForObject("http://Articles-Service/Articles/all/", ArticleList.class);
        List<ArticleModel> OriginalArticle = article.getArticle();

        List<ArticleModel> filterArticle = new ArrayList<>();
        for (ArticleModel articleModel : OriginalArticle) {

            if (articleModel.getCategory().equals(Category)) {
                filterArticle.add(articleModel);
            }
        }
        article.setArticle(filterArticle);

        if (article == null)
            return null;
        else
            return article;
	}
	
	public ArticleList relatedFallBack(String Category) {
		System.out.println("related fallBack : articles instance not available");
		ValueWrapper valueWrapper = cacheManager.getCache("articles").get(Category);
		if(valueWrapper != null ) {
			System.out.println("chached articles ");
			return (ArticleList)valueWrapper.get() ; 
		}
		System.out.println("no cached articles ");
		return new ArticleList(); 
	}
	
	
}
