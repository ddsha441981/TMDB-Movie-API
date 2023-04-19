package com.cwc.movie.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BaseConfig {


	@Bean
	RestTemplate restTemplate(RestTemplateBuilder templateBuilder) {
		return templateBuilder.build();
	}
	
	@Bean
	WebClient.Builder getWebCilent(){
		return WebClient.builder();
	}


	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
