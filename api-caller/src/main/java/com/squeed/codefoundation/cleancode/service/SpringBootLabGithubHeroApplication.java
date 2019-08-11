package com.squeed.codefoundation.cleancode.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootLabGithubHeroApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootLabGithubHeroApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
