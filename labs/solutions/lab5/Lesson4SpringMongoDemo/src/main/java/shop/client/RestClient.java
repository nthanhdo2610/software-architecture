package shop.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import shop.client.dto.CartLineDTO;
import shop.client.dto.ProductDTO;
import shop.client.dto.ShoppingCartDTO;

import java.util.Collections;

@Component
public class RestClient implements ApplicationListener<ContextRefreshedEvent> {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${base.url:http://localhost:8080}")
    private String baseUrl;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 1. Add new product to the product component
        ProductDTO newProduct = new ProductDTO();
        newProduct.setProductNumber("12345");
        newProduct.setDescription("New Product");
        newProduct.setPrice(19.99);

        ProductDTO createdProduct = restTemplate.postForObject(baseUrl + "/products", newProduct, ProductDTO.class);
        System.out.println("Added Product: " + createdProduct);

        // 2. Get the product from the product component and print to the console
        ProductDTO fetchedProduct = restTemplate.getForObject(baseUrl + "/products/" + createdProduct.getId(), ProductDTO.class);
        System.out.println("Fetched Product: " + fetchedProduct);

        // 3. Add this product to the shopping cart
        ShoppingCartDTO shoppingCart = new ShoppingCartDTO();
        shoppingCart.setItems(Collections.singletonList(new CartLineDTO(createdProduct.getId(), 2)));

        ShoppingCartDTO updatedCart = restTemplate.postForObject(baseUrl + "/shoppingcart", shoppingCart, ShoppingCartDTO.class);
        System.out.println("Updated Shopping Cart: " + updatedCart);

        // 4. Get the shopping cart and print to the console
        ShoppingCartDTO fetchedCart = restTemplate.getForObject(baseUrl + "/shoppingcart/" + updatedCart.getId(), ShoppingCartDTO.class);
        System.out.println("Fetched Shopping Cart: " + fetchedCart);
    }
}