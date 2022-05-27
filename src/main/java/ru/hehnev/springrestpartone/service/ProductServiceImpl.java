package ru.hehnev.springrestpartone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hehnev.springrestpartone.repositories.dao.ProductDao;
import ru.hehnev.springrestpartone.repositories.specification.ProductSpecification;
import ru.hehnev.springrestpartone.exceptions.ExistEntityException;
import ru.hehnev.springrestpartone.exceptions.ResourceNotFoundException;
import ru.hehnev.springrestpartone.mappers.ProductMapper;
import ru.hehnev.springrestpartone.model.dto.ProductDto;
import ru.hehnev.springrestpartone.model.entitys.Product;
import ru.hehnev.springrestpartone.validators.ProductValidator;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ProductValidator productValidator;

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> getAllProducts(Integer numPage, Integer sizePage, Integer minPrice, Integer maxPrice, String title) {
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
        return ProductMapper.MAPPER.fromProductPage(
                productDao.findAll(specification, PageRequest.of(numPage - 1, sizePage)));
    }

    @Override
    public ProductDto getProductById(Long id) {
        return ProductMapper.MAPPER.fromProductDto(productDao
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + "is not found")));
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto product) {
        log.info(product.toString());
        productValidator.validate(product);
        if (product.getId() == null) {
            if (productDao.existsProductByTitle(product.getTitle())) {
                throw new ExistEntityException("This product already exists");
            }
        }
        if (!productDao.existsProductById(product.getId())) {
            throw new ResourceNotFoundException("Product with id: " + product.getId() + " is not found");
        }
        return ProductMapper.MAPPER.fromProductDto(productDao.save(ProductMapper.MAPPER.toProduct(product)));
    }

    @Override
    public void deleteProductById(Long id) {
        log.info("delete product by id {}", id);
        productDao.deleteById(id);
    }
}
