package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StreamBridge streamBridge;

    public Product addProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        ProductChangedEvent event = new ProductChangedEvent(savedProduct.getProductNumber(), savedProduct.getName(), savedProduct.getPrice(), 0);
        streamBridge.send("productChange-out-0", event);

        return savedProduct;
    }

    public Product updateProduct(String productNumber, Product product) {
        Product existingProduct = productRepository.findByProductNumber(productNumber);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);

        ProductChangedEvent event = new ProductChangedEvent(updatedProduct.getProductNumber(), updatedProduct.getName(), updatedProduct.getPrice(), 0);
        streamBridge.send("productChange-out-0", event);

        return updatedProduct;
    }

    public void deleteProduct(String productNumber) {
        productRepository.deleteByProductNumber(productNumber);

        ProductChangedEvent event = new ProductChangedEvent(productNumber, null, 0, 0);
        streamBridge.send("productChange-out-0", event);
    }
}