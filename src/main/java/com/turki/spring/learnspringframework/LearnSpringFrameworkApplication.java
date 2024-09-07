package com.turki.spring.learnspringframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 */		
@SpringBootApplication
public class LearnSpringFrameworkApplication {

	public static void main(String[] args) {
		//ConfigurableApplicationContext context = SpringApplication.run(LearnSpringFrameworkApplication.class, args); //this context to manage all my beans.
		//GameRunner runner = context.getBean(GameRunner.class); //this to get the obj of GameRunner and use it's methods.
		SpringApplication.run(LearnSpringFrameworkApplication.class, args); //this context to manage all my beans.

		

	}

}
