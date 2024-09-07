package com.turki.spring.learnspringframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.turki.spring.learnspringframework.enterprise.example.web.MyWebController;
import com.turki.spring.learnspringframework.game.GameRunner;

/**
 * 
 */
@SpringBootApplication
public class LearnSpringFrameworkApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LearnSpringFrameworkApplication.class, args); //this context to manage all my beans.

		//		MarioGame game = new MarioGame();
		//		SuperContraGame game = new SuperContraGame();
		//		GamingConsole game = new SuperContraGame();
		//		GameRunner runner = new GameRunner(game);

		GameRunner runner = context.getBean(GameRunner.class);

		runner.run();

		MyWebController controller = context.getBean(MyWebController.class);
		long businessServiceValue = controller.getBusinessService();
		System.out.println("The Business Value= "+businessServiceValue);
	}

}
