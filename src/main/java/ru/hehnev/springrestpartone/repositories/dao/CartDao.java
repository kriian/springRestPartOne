package ru.hehnev.springrestpartone.repositories.dao;

import ru.hehnev.springrestpartone.model.entitys.Product;

import java.util.Map;

public interface CartDao {
    Map<Product, Integer> getProducts();
    Product getProductById(Long id);
    void addProduct(Product product);
    void deleteProductById(Long id);
}
