package com.turki.spring.learnspringframework.enterprise.example.web;

import org.springframework.stereotype.Component;

import com.turki.spring.learnspringframework.enterprise.example.business.BusinessService;

@Component
public class MyWebController {
	
	private BusinessService businessService;
	
	public MyWebController(BusinessService businessService){
		super();
		this.businessService = businessService;
	}
	public long getBusinessService() {
		return businessService.calculateSum();
	}
}




