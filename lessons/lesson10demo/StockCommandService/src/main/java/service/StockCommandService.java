package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class StockCommandService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Stock addStock(Stock stock) {
        Stock savedStock = stockRepository.save(stock);

        // Emit event
        Product product = productRepository.findByProductNumber(stock.getProductNumber());
        if (product != null) {
            eventPublisher.publishEvent(new ProductChangedEvent(product.getProductNumber(), product.getName(), product.getPrice(), stock.getQuantity()));
        }

        return savedStock;
    }

    public Stock updateStock(String productNumber, Stock stock) {
        Stock existingStock = stockRepository.findByProductNumber(productNumber);
        existingStock.setQuantity(stock.getQuantity());

        Stock updatedStock = stockRepository.save(existingStock);

        // Emit event
        Product product = productRepository.findByProductNumber(productNumber);
        if (product != null) {
            eventPublisher.publishEvent(new ProductChangedEvent(product.getProductNumber(), product.getName(), product.getPrice(), stock.getQuantity()));
        }

        return updatedStock;
    }

    public void deleteStock(String productNumber) {
        stockRepository.deleteByProductNumber(productNumber);

        // Emit event with 0 quantity (as stock is deleted)
        Product product = productRepository.findByProductNumber(productNumber);
        if (product != null) {
            eventPublisher.publishEvent(new ProductChangedEvent(product.getProductNumber(), product.getName(), product.getPrice(), 0));
        }
    }
}