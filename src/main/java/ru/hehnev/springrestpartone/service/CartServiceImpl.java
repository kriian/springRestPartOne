package ru.hehnev.springrestpartone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hehnev.springrestpartone.mappers.ProductMapper;
import ru.hehnev.springrestpartone.model.dto.ProductDto;
import ru.hehnev.springrestpartone.repositories.dao.CartDao;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartDao cartDao;

    @Override
    public Map<ProductDto, Integer> getProducts() {
        return ProductMapper.MAPPER.fromProductMap(cartDao.getProducts());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return ProductMapper.MAPPER.fromProductDto(cartDao.getProductById(id));
    }

    @Override
    public void addProduct(ProductDto product) {
        cartDao.addProduct(ProductMapper.MAPPER.toProduct(product));
    }

    @Override
    public void deleteProductById(Long id) {
        cartDao.deleteProductById(id);
    }
}
