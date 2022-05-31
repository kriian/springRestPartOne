package ru.hehnev.springrestpartone.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.hehnev.springrestpartone.model.dto.ProductDto;
import ru.hehnev.springrestpartone.model.entitys.Product;
import ru.hehnev.springrestpartone.service.CartService;
import ru.hehnev.springrestpartone.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "np" ,defaultValue = "1") Integer numPage,
                                        @RequestParam(name = "sp", defaultValue = "5") Integer sizePage,
                                        @RequestParam(name = "min", required = false) Integer minPrice,
                                        @RequestParam(name = "max", required = false) Integer maxPrice,
                                        @RequestParam(name = "title", required = false) String title) {
        return productService.getAllProducts(numPage, sizePage, minPrice, maxPrice, title);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto product) {
        return productService.saveOrUpdate(product);
    }

    @RequestMapping("/add_cart/{id}")
    public RedirectView addProductToCart(@PathVariable Long id) throws IOException {
        cartService.addProduct(getProductById(id));
        return new RedirectView("/app/api/v1/products/cart");
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}

