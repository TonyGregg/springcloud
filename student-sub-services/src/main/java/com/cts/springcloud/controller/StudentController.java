package com.cts.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@RequestMapping(value="history",method=RequestMethod.GET)
	public String getHistory() {
		
		return "previous history from subsystem";
	}
	
	
	

}
