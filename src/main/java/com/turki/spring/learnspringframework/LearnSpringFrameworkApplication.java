package com.turki.spring.learnspringframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 */		
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.turki.spring.learnspringframework.Model")
//@EnableJpaRepositories(basePackages = "com.turki.spring.learnspringframework.Model")
public class LearnSpringFrameworkApplication {

	public static void main(String[] args) {
		//ConfigurableApplicationContext context = SpringApplication.run(LearnSpringFrameworkApplication.class, args); //this context to manage all my beans.
		//GameRunner runner = context.getBean(GameRunner.class); //this to get the obj of GameRunner and use it's methods.
		SpringApplication.run(LearnSpringFrameworkApplication.class, args); //this context to manage all my beans.

		

	}

}

