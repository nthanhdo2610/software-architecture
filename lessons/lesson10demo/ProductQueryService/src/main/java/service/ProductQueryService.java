package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    // Event Listener for ProductChangedEvent
    @Bean
    public Consumer<ProductChangedEvent> productChange() {
        return event -> {
            // Process product changes and update MongoDB
            Product product = productRepository.findByProductNumber(event.getProductNumber());

            if (event.getName() == null && event.getPrice() == 0 && event.getQuantity() == 0) {
                // If name, price, and quantity are null/zero, delete the product
                productRepository.deleteByProductNumber(event.getProductNumber());
                stockRepository.deleteByProductNumber(event.getProductNumber());
            } else {
                if (product == null) {
                    // Add new product
                    product = new Product(event.getProductNumber(), event.getName(), event.getPrice());
                } else {
                    // Update existing product
                    product.setName(event.getName());
                    product.setPrice(event.getPrice());
                }
                productRepository.save(product);

                // Update or create stock
                Stock stock = stockRepository.findByProductNumber(event.getProductNumber());
                if (stock == null) {
                    stock = new Stock(event.getProductNumber(), event.getQuantity());
                } else {
                    stock.setQuantity(event.getQuantity());
                }
                stockRepository.save(stock);
            }
        };
    }

    public List<ProductWithStock> getAllProductsWithStock() {
        List<Product> products = productRepository.findAll();
        List<ProductWithStock> productWithStocks = new ArrayList<>();

        for (Product product : products) {
            Stock stock = stockRepository.findByProductNumber(product.getProductNumber());
            int quantity = (stock != null) ? stock.getQuantity() : 0;
            productWithStocks.add(new ProductWithStock(product.getProductNumber(), product.getName(), product.getPrice(), quantity));
        }

        return productWithStocks;
    }
}