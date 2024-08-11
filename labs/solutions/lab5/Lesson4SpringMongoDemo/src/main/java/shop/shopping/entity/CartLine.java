package shop.shopping.entity;

import lombok.Data;

@Data
public class CartLine {
    private String productId;
    private int quantity;
}