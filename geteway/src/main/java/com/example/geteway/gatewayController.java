package com.example.geteway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class gatewayController {

	@Value("${eureka.instance.metadataMap.zone}")
	private String zone;
	@RequestMapping(method = RequestMethod.GET , value = "/ping")
	public String Ping() {
		return zone;
	}
}
