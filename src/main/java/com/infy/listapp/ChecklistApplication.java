package com.infy.listapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ChecklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChecklistApplication.class, args);
	}

}
