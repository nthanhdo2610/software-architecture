package shop.shopping.service;

import org.springframework.stereotype.Service;
import shop.shopping.dto.CartLineDTO;
import shop.shopping.dto.ShoppingCartDTO;
import shop.shopping.entity.CartLine;
import shop.shopping.entity.ShoppingCart;
import shop.shopping.repository.ShoppingCartRepository;

import java.util.stream.Collectors;

@Service
public class ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartDTO addToShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setItems(shoppingCartDTO.getItems().stream().map(item -> {
            CartLine cartLine = new CartLine();
            cartLine.setProductId(item.getProductId());
            cartLine.setQuantity(item.getQuantity());
            return cartLine;
        }).collect(Collectors.toList()));

        shoppingCart = shoppingCartRepository.save(shoppingCart);

        shoppingCartDTO.setId(shoppingCart.getId());
        return shoppingCartDTO;
    }

    public ShoppingCartDTO getShoppingCart(String id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(null);
        if (shoppingCart == null) {
            return null;
        }
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setItems(shoppingCart.getItems().stream().map(item -> {
            CartLineDTO cartLineDTO = new CartLineDTO();
            cartLineDTO.setProductId(item.getProductId());
            cartLineDTO.setQuantity(item.getQuantity());
            return cartLineDTO;
        }).collect(Collectors.toList()));
        return shoppingCartDTO;
    }
}
