package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class StockCommandService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StreamBridge streamBridge;

    public Stock addStock(Stock stock) {
        Stock savedStock = stockRepository.save(stock);

        ProductChangedEvent event = new ProductChangedEvent(stock.getProductNumber(), "", 0, savedStock.getQuantity());
        streamBridge.send("productChange-out-0", event);

        return savedStock;
    }

    public Stock updateStock(String productNumber, Stock stock) {
        Stock existingStock = stockRepository.findByProductNumber(productNumber);
        existingStock.setQuantity(stock.getQuantity());

        Stock updatedStock = stockRepository.save(existingStock);

        ProductChangedEvent event = new ProductChangedEvent(productNumber, "", 0, updatedStock.getQuantity());
        streamBridge.send("productChange-out-0", event);

        return updatedStock;
    }

    public void deleteStock(String productNumber) {
        stockRepository.deleteByProductNumber(productNumber);

        ProductChangedEvent event = new ProductChangedEvent(productNumber, null, 0, 0);
        streamBridge.send("productChange-out-0", event);
    }
}