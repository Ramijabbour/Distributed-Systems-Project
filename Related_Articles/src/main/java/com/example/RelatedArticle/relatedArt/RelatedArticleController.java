package com.example.RelatedArticle.relatedArt;

import com.example.RelatedArticle.Article.ArticleList;
import com.example.RelatedArticle.Article.ArticleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RelatedArticleController {

    @Autowired
    public RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/RelatedArticle/{Category}")
    public ArticleList findRelatedArticle(@PathVariable String Category) {
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


}
