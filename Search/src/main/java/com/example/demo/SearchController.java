package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

	@Autowired 
	private SearchService searchService ; 
   

    @RequestMapping(method = RequestMethod.GET, value = "/Search/{subject}")
    public ArticleList Search(@PathVariable String subject) {
       return  this.searchService.searchForArticles(subject);
    }


}
