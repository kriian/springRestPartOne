package ru.hehnev.springrestpartone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.hehnev.springrestpartone.dao.ProductDao;
import ru.hehnev.springrestpartone.dao.specification.ProductSpecification;
import ru.hehnev.springrestpartone.exceptions.ExistEntityException;
import ru.hehnev.springrestpartone.exceptions.ResourceNotFoundException;
import ru.hehnev.springrestpartone.model.entitys.Product;


@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Page<Product> getAllProducts(Integer numPage, Integer sizePage, Integer minPrice, Integer maxPrice, String title) {
        if (numPage < 1) numPage = 1;
        if (sizePage < 1) sizePage = 1;
        Specification<Product> specification = Specification.where(null);
        if (minPrice != null) {
            specification = specification.and(ProductSpecification.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecification.priceLessThanOrEqualTo(maxPrice));
        }
        if (title != null) {
            specification = specification.and(ProductSpecification.likeTitle(title));
        }
        return productDao.findAll(specification, PageRequest.of(numPage - 1, sizePage));
    }

    @Override
    public Product getProductById(Long id) {
        return productDao
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + "is not found"));
    }

    @Override
    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            if (productDao.existsProductByTitle(product.getTitle())) {
                throw new ExistEntityException("This product is already");
            }
        }
        return productDao.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productDao.deleteById(id);
    }
}
