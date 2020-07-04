package com.example.Articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ExternalModel.ArticleComment;
import com.example.ExternalModel.CommentModel;
import com.example.MQ.OrderMessageSender;
import com.example.MQ.ModelToSend.ArticleID;

@RestController
@RequestMapping("/Articles")
public class ArticleController {
	@Autowired 
	ArticleService articleService;

	@Autowired
	public RestTemplate restTemplate ;
	
	@Autowired
	private OrderMessageSender orderMessageSender ; 
	
	@Value("${eureka.instance.metadataMap.zone}")
	private String zone;
	@RequestMapping(method = RequestMethod.GET , value = "/ping")
	public String Ping() {
		return zone;
	}

	@RequestMapping(method = RequestMethod.GET , value = "/all")
	public ArticleList allArticle()
	{
		List <ArticleModel> allArticle = this.articleService.GetAllArticles();
		ArticleList articleList = new ArticleList();
		articleList.setArticle(allArticle);
		return articleList;
	}

	@RequestMapping(method = RequestMethod.POST , value = "/addArticle")
	public void addArticle(@RequestBody ArticleModel article)
	{
		articleService.addArticle(article);
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/Show/{id}")
	public ArticleCommentRating ShowArticle(@PathVariable int id)
	{
		System.out.println("call for articles service ---------------------->");
		ArticleModel article = this.articleService.getArticlesByID(id);
		if(article == null )
			return null ; 
		
		//get all comment for this article 
		ArticleComment AllComments =restTemplate.getForObject("http://Comments-Service/Comments/getComments/"+article.id, ArticleComment.class);
		List<CommentModel> ArticleComment = AllComments.getAllCommentForThisArticle();
		float Rating =restTemplate.getForObject("http://Rating-Service/Rate/getRate/"+article.id, Float.class);
	
		ArticleCommentRating AllInformation = new ArticleCommentRating();
		AllInformation.setArticle(article);
		AllInformation.setComment(ArticleComment);
		AllInformation.setRating(Rating);
		System.out.println("End of articles service ---------------------->");
		return AllInformation;
	}
	
	
	@RequestMapping(method = RequestMethod.GET , value = "/delete/{id}")
	public String deleteArticle(@PathVariable int id)
	{
		this.articleService.deleteArticle(id);
		orderMessageSender.sendOrderToComment(new ArticleID(id));
		return "ok";
	}


	@RequestMapping(method = RequestMethod.GET , value = "/ArticleSearch/{subject}")
	public ArticleList Search(@PathVariable String subject)
	{
		List <ArticleModel> allArticle = this.articleService.GetSearchResult(subject);
		ArticleList articleList= new ArticleList();
		articleList.setArticle(allArticle);
		return articleList;
	}

	@RequestMapping(method = RequestMethod.GET , value = "/test")
	public ArticleModel test()
	{

		ArticleModel a = new ArticleModel();
		a.setSubject("test");
		a.setText("sdghdgsdfdg");
		return  a;

	}


    @RequestMapping(method = RequestMethod.GET , value = "/inject")
    public void inject()
    {


        ArticleModel a3 = new ArticleModel("كسر العظم","عن العظم","طبية","مقال عن كسر العظم و شرح كسر الساق ....");
        articleService.addArticle(a3);
        ArticleModel a4 = new ArticleModel("فيروس كورونا","فيروسات","طبية","فيروس كورونا من اخطر الفيروسات ....");
        articleService.addArticle(a4);
        ArticleModel a5 = new ArticleModel("مرض السارس","جراثيم و فيروسات","طبية","ظهر مرض السارس في القرن ...");
        articleService.addArticle(a5);
        ArticleModel a6= new ArticleModel("الغدقة الدرقية","بكتيرياوجراثيم","جراحة","يعد مرض الغدة الدرقية من الامراض....");
        articleService.addArticle(a6);
//        ArticleModel a7= new ArticleModel();
//        ArticleModel a8= new ArticleModel();
//        ArticleModel a9= new ArticleModel();
//        ArticleModel a10= new ArticleModel();
//        ArticleModel a11= new ArticleModel();
//        ArticleModel a12= new ArticleModel();
//        ArticleModel a13= new ArticleModel();
//        ArticleModel a14 = new ArticleModel();
//        ArticleModel a15 = new ArticleModel();
//
//        articleService.addArticle();

    }


}
