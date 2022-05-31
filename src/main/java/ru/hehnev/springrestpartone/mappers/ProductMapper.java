package ru.hehnev.springrestpartone.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ru.hehnev.springrestpartone.model.dto.ProductDto;
import ru.hehnev.springrestpartone.model.entitys.Product;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDto productDto);
    @InheritInverseConfiguration
    ProductDto fromProductDto(Product product);
    List<ProductDto> fromProductList(List<Product> products);
    default Page<ProductDto> fromProductPage(Page<Product> products) {
        return new PageImpl<>(fromProductList(products.toList()), products.getPageable(), products.getTotalElements());
    }

    Map<ProductDto, Integer> fromProductMap(Map<Product, Integer> products);

}
