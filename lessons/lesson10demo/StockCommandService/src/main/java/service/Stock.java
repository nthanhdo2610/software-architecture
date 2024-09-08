package service;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "stocks")
public class Stock {
    @Id
    private String productNumber;
    private int quantity;
}
