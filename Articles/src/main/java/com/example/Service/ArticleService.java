package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Models.articles;
import com.example.Repository.ArticleRepository;

@Service 
public class ArticleService {

	@Autowired 
	ArticleRepository articleRepo ; 
	
	public articles addArticle (articles a)
	{
		return articleRepo.save(a);
	}
	
	
}
