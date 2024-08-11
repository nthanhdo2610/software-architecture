package shop.shopping.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class ShoppingCart {
    @Id
    private String id;
    private List<CartLine> items;
}
