package ru.hehnev.springrestpartone.repositories.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.hehnev.springrestpartone.exceptions.ResourceNotFoundException;
import ru.hehnev.springrestpartone.model.entitys.Product;
import ru.hehnev.springrestpartone.repositories.local.CartRepository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartDaoImpl implements CartDao{

    private final CartRepository cartRepository;
    private Map<Product, Integer> products;
    private int count;

    @PostConstruct
    public void init() {
        products = cartRepository.getProducts();
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        return products.keySet()
                .stream()
                .filter(integer -> integer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + "is not found"));
    }

    @Override
    public void addProduct(Product product) {
        log.info(products.containsKey(product) + "");
        if (products.containsKey(product)) {
            count++;
        } else {
            count = 1;
        }
        products.put(product, count);
    }

    @Override
    public void deleteProductById(Long id) {
        products.keySet()
                .removeIf(product -> product.getId().equals(id));
    }
}
