package com.cts.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class StudentController {
	
	@Value("${name}")
	private String name;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@RequestMapping(value="/history",method=RequestMethod.GET)
	public String getHistory() {
		
		return "previous history from subsystem" ;
	}
	
	@RequestMapping(value="/config-test",method=RequestMethod.GET)
	public String test() {
		
		return name;
	}
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String testName() {
		
		return appName;
	}
	
	
	

}
