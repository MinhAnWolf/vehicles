package com.example.portal;

import com.example.business.model.test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalApplication {

	public static void main(String[] args) {
		System.out.println(test.test());
		SpringApplication.run(PortalApplication.class, args);
	}

}
