package com.example.WebApp.WebApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/wiki")
public class AppController {

	@RequestMapping(method = RequestMethod.GET , value ="/home")
	public ModelAndView getHomePage() {
		ModelAndView mav = new ModelAndView("addArticle");
		return mav  ;
	}
}
