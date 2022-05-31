package ru.hehnev.springrestpartone.repositories.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.hehnev.springrestpartone.model.entitys.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    boolean existsProductByTitle(String title);

    boolean existsProductById(Long id);
}
