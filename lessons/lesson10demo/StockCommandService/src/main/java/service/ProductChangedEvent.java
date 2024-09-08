package service;

import lombok.Data;

@Data
public class ProductChangedEvent {
    private String productNumber;
    private String name;
    private double price;
    private int quantity;

    // Constructor, getters, setters
    public ProductChangedEvent(String productNumber, String name, double price, int quantity) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
