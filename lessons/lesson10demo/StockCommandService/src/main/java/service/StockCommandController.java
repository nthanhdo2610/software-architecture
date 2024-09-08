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
@RequestMapping("/stock")
public class StockCommandController {

    @Autowired
    private StockCommandService stockCommandService;

    @PostMapping
    public Stock addStock(@RequestBody Stock stock) {
        return stockCommandService.addStock(stock);
    }

    @PutMapping("/{productNumber}")
    public Stock updateStock(@PathVariable String productNumber, @RequestBody Stock stock) {
        return stockCommandService.updateStock(productNumber, stock);
    }

    @DeleteMapping("/{productNumber}")
    public void deleteStock(@PathVariable String productNumber) {
        stockCommandService.deleteStock(productNumber);
    }
}