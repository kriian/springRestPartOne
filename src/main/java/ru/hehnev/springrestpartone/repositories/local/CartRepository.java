package ru.hehnev.springrestpartone.repositories.local;

import org.springframework.stereotype.Repository;
import ru.hehnev.springrestpartone.exceptions.ResourceNotFoundException;
import ru.hehnev.springrestpartone.model.entitys.Product;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepository {

    private Map<Product, Integer> products;
    private int count;

    @PostConstruct
    public void init() {
        products = new HashMap<>();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}
