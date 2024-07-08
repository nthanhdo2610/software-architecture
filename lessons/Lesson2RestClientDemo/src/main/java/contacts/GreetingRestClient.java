package greeting;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Component
public class GreetingRestClient {
	@Autowired
	private RestOperations restTemplate;
	private String serverUrl = "http://localhost:8080/greeting";

	public void callRestServer() {
		

	    Greeting  greeting= restTemplate.getForObject(serverUrl+"/{message}", Greeting.class, " from Spring");
	    System.out.println(greeting.getContent());



	}
}




