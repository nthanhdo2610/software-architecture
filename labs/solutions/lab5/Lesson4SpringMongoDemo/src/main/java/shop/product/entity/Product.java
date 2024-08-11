package shop.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    @Id
    private String id;
    private String productNumber;
    private String description;
    private double price;
}
