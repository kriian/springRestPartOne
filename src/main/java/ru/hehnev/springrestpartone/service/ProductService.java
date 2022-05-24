package ru.hehnev.springrestpartone.service;

import org.springframework.data.domain.Page;
import ru.hehnev.springrestpartone.model.entitys.Product;

import java.util.List;

public interface ProductService {
    Page<Product> getAllProducts(Integer numPage, Integer sizePage, Integer minPrice, Integer maxPrice, String title);
    Product getProductById(Long id);
    Product saveOrUpdate(Product product);
    void deleteProductById(Long id);
}
