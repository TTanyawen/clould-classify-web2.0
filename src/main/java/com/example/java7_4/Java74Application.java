package com.example.java7_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
//@EnableSwagger2WebMvc
//@CrossOrigin("*")
@EnableScheduling
public class Java74Application {

	public static void main(String[] args) {
		SpringApplication.run(Java74Application.class, args);
	}

}
