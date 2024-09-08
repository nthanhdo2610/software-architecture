package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Bean
    public Consumer<ProductChangedEvent> productChange() {
        return event -> {
            // Update product and stock in the query-side database
            if (event.getName() == null && event.getPrice() == 0 && event.getQuantity() == 0) {
                // Product is deleted
                productRepository.deleteByProductNumber(event.getProductNumber());
                stockRepository.deleteByProductNumber(event.getProductNumber());
            } else {
                // Update or add the product in the query database
                Product product = productRepository.findByProductNumber(event.getProductNumber());

                if (product == null) {
                    product = new Product();
                    product.setProductNumber(event.getProductNumber());
                }

                product.setName(event.getName());
                product.setPrice(event.getPrice());
                productRepository.save(product);

                // Update the stock as well
                Stock stock = stockRepository.findByProductNumber(event.getProductNumber());
                if (stock == null) {
                    stock = new Stock();
                    stock.setProductNumber(event.getProductNumber());
                }

                stock.setQuantity(event.getQuantity());
                stockRepository.save(stock);
            }
        };
    }
}