package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    OAuth2RestTemplate restTemplate;


    @GetMapping("/name")
    public String getName() {
        return "Frank Brown";
    }

    @GetMapping("/salary")
    public String getSalary() {
        return restTemplate.getForObject("http://localhost:8092/salary", String.class);
    }


    @GetMapping("/phone")
    public String getPhone() {
        return restTemplate.getForObject("http://localhost:8091/phone", String.class);
    }
}

