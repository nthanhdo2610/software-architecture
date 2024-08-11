package esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class ShippingActivator {
	@Autowired
	RestTemplate restTemplate;

	public void nextDayShip(Order order) {
		System.out.println("next day shipping: "+ order.toString());
		restTemplate.postForLocation("http://localhost:8081/orders", order);
	}

	public void normalShip(Order order) {
		System.out.println("normal shipping: "+ order.toString());
		restTemplate.postForLocation("http://localhost:8081/orders", order);
	}

	public void internationalShip(Order order) {
		System.out.println("international shipping: "+ order.toString());
		restTemplate.postForLocation("http://localhost:8081/orders", order);
	}
}
