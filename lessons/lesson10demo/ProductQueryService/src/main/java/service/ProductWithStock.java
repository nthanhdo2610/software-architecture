package service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductWithStock {
    private String productNumber;
    private String name;
    private double price;
    private int quantityInStock;

    public ProductWithStock(Product product, int quantityInStock) {
        this.productNumber = product.getProductNumber();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantityInStock = quantityInStock;
    }
}
