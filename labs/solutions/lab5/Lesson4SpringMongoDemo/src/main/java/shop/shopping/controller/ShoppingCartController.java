package shop.shopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.shopping.dto.ShoppingCartDTO;
import shop.shopping.service.ShoppingCartService;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public ShoppingCartDTO addToShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartService.addToShoppingCart(shoppingCartDTO);
    }

    @GetMapping("/{id}")
    public ShoppingCartDTO getShoppingCart(@PathVariable String id) {
        return shoppingCartService.getShoppingCart(id);
    }
}