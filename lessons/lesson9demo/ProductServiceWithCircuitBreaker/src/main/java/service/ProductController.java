package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @RequestMapping("/text")
    public String getText() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        String stockServiceText = circuitBreaker.run(() -> restTemplate.getForObject("http://localhost:9091/text",
                String.class), throwable -> getFallbackName());
        return "Hello " + stockServiceText;
    }

    private String getFallbackName() {
        return "Fallback World";
    }

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
