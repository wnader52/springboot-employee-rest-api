package com.sample.spring.employee.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		System.out.println("http://localhost:8082/swagger-ui/index.html");
		SpringApplication.run(Application.class, args);
	}

}
