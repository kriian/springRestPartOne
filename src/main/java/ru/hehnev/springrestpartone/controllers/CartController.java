package ru.hehnev.springrestpartone.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.hehnev.springrestpartone.model.dto.ProductDto;
import ru.hehnev.springrestpartone.service.CartService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Map<ProductDto, Integer> getProducts() {
        return cartService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return cartService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public RedirectView deleteProductById(@PathVariable Long id) {
        cartService.deleteProductById(id);
        return new RedirectView("/app/api/v1/products/cart");
    }

}
