package stock;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
	@RequestMapping("/stock/{productId}")
	public Product getId(@PathVariable("productId") String productId) {
		return new Product("1234", "1000.00");
	}
}
