package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    @Autowired
    private ProductCommandService productCommandService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productCommandService.addProduct(product);
    }

    @PutMapping("/{productNumber}")
    public Product updateProduct(@PathVariable String productNumber, @RequestBody Product product) {
        return productCommandService.updateProduct(productNumber, product);
    }

    @DeleteMapping("/{productNumber}")
    public void deleteProduct(@PathVariable String productNumber) {
        productCommandService.deleteProduct(productNumber);
    }
}