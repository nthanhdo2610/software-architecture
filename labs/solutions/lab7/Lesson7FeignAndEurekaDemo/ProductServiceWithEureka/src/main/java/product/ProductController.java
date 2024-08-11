package product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductFeignClient productClient;

    @RequestMapping("/product/{id}")
    public Product getName(@PathVariable("id") String id) {
        return productClient.getId(id);
    }

    @FeignClient(name = "StockService")
    interface ProductFeignClient {
        @RequestMapping("/stock/{productId}")
        Product getId(@PathVariable("productId") String productId);
    }

}
