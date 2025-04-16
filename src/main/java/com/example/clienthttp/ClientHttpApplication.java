package com.example.clienthttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientHttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientHttpApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new org.springframework.http.converter.StringHttpMessageConverter());
        return restTemplate;
    }

}
