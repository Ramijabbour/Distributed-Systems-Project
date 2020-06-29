package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Models.articles;
import com.example.Service.ArticleService;

@RestController
@RequestMapping("/Articles")
public class ArticleController {

	@Autowired 
	ArticleService articleService ; 
	
	
	@PostMapping("/add")
	public articles addArticle(@RequestBody articles a)
	{
		return articleService.addArticle(a);
				
				
		
	}
	
	
}
