package ru.hehnev.springrestpartone.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.hehnev.springrestpartone.model.entitys.Product;

public class ProductSpecification {

    public static Specification<Product> priceGreaterThanOrEqualTo(Integer minPrice) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
    }

    public static Specification<Product> priceLessThanOrEqualTo(Integer maxPrice) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
    }

    public static Specification<Product> likeTitle(String title) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), title));
    }
}
