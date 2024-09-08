package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StreamBridge streamBridge;

    public Product addProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        // Get stock quantity
        Stock stock = stockRepository.findByProductNumber(product.getProductNumber());
        int stockQuantity = (stock != null) ? stock.getQuantity() : 0;

        // Publish event to Kafka
        streamBridge.send("productChange-out-0", new ProductChangedEvent(
                product.getProductNumber(), product.getName(), product.getPrice(), stockQuantity
        ));

        return savedProduct;
    }

    public Product updateProduct(String productNumber, Product product) {
        Product existingProduct = productRepository.findByProductNumber(productNumber);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);

        // Get stock quantity
        Stock stock = stockRepository.findByProductNumber(productNumber);
        int stockQuantity = (stock != null) ? stock.getQuantity() : 0;

        // Publish event to Kafka
        streamBridge.send("productChange-out-0", new ProductChangedEvent(
                product.getProductNumber(), product.getName(), product.getPrice(), stockQuantity
        ));

        return updatedProduct;
    }

    public void deleteProduct(String productNumber) {
        productRepository.deleteByProductNumber(productNumber);

        // Publish deletion event to Kafka
        streamBridge.send("productChange-out-0", new ProductChangedEvent(
                productNumber, null, 0, 0
        ));
    }
}