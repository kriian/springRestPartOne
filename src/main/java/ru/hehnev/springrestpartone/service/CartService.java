package ru.hehnev.springrestpartone.service;

import ru.hehnev.springrestpartone.model.dto.ProductDto;

import java.util.Map;

public interface CartService {
    Map<ProductDto, Integer> getProducts();
    ProductDto getProductById(Long id);
    void addProduct(ProductDto product);
    void deleteProductById(Long id);
}
