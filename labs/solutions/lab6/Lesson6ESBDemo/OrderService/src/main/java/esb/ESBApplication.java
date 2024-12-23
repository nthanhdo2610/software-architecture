package esb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ESBApplication implements CommandLineRunner {
	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ESBApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		restTemplate.postForLocation("http://localhost:8080/orders", new Order("333", 120.0));
		restTemplate.postForLocation("http://localhost:8080/orders", new Order("444", 185.0));
		restTemplate.postForLocation("http://localhost:8080/orders", new Order("555", 165.0, "international"));
		restTemplate.postForLocation("http://localhost:8080/orders", new Order("666", 135.0, "international"));
	}
}
