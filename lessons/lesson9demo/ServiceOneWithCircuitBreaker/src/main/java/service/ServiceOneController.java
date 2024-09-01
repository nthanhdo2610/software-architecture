package service;



import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@RestController
public class ServiceOneController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @RequestMapping("/text")
    public String getText() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        String service2Text = circuitBreaker.run(() -> restTemplate.getForObject("http://localhost:9091/text",
                String.class), throwable -> getFallbackName());
        return "Hello "+ service2Text;
    }

    private String getFallbackName() {
        return "Fallback World";
    }

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
