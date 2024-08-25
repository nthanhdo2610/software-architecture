package product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {
    @Autowired
    StockFeignClient stockClient;

    @RequestMapping("/product/{id}")
    public Product getName(@PathVariable("id") String id) {
        return stockClient.getId(id);
    }

    @FeignClient(name = "StockService")
    interface StockFeignClient {
        @RequestMapping("/stock/{id}")
        Product getId(@PathVariable("id") String id);
    }

}
