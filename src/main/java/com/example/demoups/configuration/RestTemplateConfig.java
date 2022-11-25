package com.example.demoups.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	//This bean is to configure and create a RestTemplate to consume a service
	@Bean
	public RestTemplate restTemplate(final RestTemplateBuilder builder) {
		return builder.build();
	}
	
}
