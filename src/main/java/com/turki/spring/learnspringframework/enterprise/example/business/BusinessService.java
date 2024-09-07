package com.turki.spring.learnspringframework.enterprise.example.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turki.spring.learnspringframework.enterprise.example.data.DataService;

@Component

public class BusinessService{

	private DataService dataService;
	@Autowired
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	public long calculateSum() {
		List<Integer> data = dataService.getData();
		return data.stream().reduce(Integer::sum).get();
	}
}

