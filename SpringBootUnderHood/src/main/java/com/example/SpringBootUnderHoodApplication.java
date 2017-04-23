package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootUnderHoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUnderHoodApplication.class, args);
	}
	
	@Bean
	@ConditionalOnClass(name="java.lang.String")
	public String string(){
		String method = "string()";
		System.err.println("Method "+ method+" has been called");
		return method;
	}
	
	
	@Bean
	@ConditionalOnMissingClass("java.lang.String")
	public String missingString(){
		String method = "missingString()";
		System.err.println("Method "+ method+" has been called");
		return method;
	}
}
