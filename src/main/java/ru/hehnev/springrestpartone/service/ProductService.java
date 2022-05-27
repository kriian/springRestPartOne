package ru.hehnev.springrestpartone.service;

import org.springframework.data.domain.Page;
import ru.hehnev.springrestpartone.model.dto.ProductDto;
import ru.hehnev.springrestpartone.model.entitys.Product;

import java.util.List;

public interface ProductService {
    Page<ProductDto> getAllProducts(Integer numPage, Integer sizePage, Integer minPrice, Integer maxPrice, String title);
    ProductDto getProductById(Long id);
    ProductDto saveOrUpdate(ProductDto productDto);
    void deleteProductById(Long id);
}
