package stock;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
	@RequestMapping("/stock/{id}")
	public Product getId(@PathVariable("id") String id) {
		return new Product(id, "2000.00");
	}
}
