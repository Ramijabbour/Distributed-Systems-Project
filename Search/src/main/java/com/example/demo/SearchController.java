package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    public RestTemplate restTemplate;


    @RequestMapping(method = RequestMethod.GET, value = "/Search/{subject}")
    public ArticleList Search(@PathVariable String subject) {
        ArticleList article = restTemplate.getForObject("http://Articles-Service/Articles/ArticleSearch/" + subject, ArticleList.class);
        if (article == null)
            return null;
        else
            return article;
    }

}
