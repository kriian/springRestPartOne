package ru.hehnev.springrestpartone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.hehnev.springrestpartone.model.entitys.Product;
import ru.hehnev.springrestpartone.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(name = "np" ,defaultValue = "1") Integer numPage,
                                        @RequestParam(name = "sp", defaultValue = "5") Integer sizePage,
                                        @RequestParam(name = "min", required = false) Integer minPrice,
                                        @RequestParam(name = "max", required = false) Integer maxPrice,
                                        @RequestParam(name = "title", required = false) String title) {
        return productService.getAllProducts(numPage, sizePage, minPrice, maxPrice, title);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}

