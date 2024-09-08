package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @EventListener
    public void handleProductChangedEvent(ProductChangedEvent event) {
        // Handle product change and update the query-side database
        if (event.getName() == null && event.getPrice() == 0 && event.getQuantity() == 0) {
            // Product is deleted
            productRepository.deleteByProductNumber(event.getProductNumber());
            stockRepository.deleteByProductNumber(event.getProductNumber());
        } else {
            // Update or add the product in the query database
            Product product = productRepository.findByProductNumber(event.getProductNumber());

            if (product == null) {
                // New product case
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
    }
}