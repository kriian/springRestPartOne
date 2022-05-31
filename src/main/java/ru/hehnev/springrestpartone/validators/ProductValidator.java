package ru.hehnev.springrestpartone.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.hehnev.springrestpartone.exceptions.ValidateException;
import ru.hehnev.springrestpartone.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        log.info(errors.toString());
        if (productDto.getPrice() < 1) {
            errors.add("Цена товара не может быть меньше 1");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Название товара не может быть пустым");
        }
        if (!errors.isEmpty()) {
            throw new ValidateException(errors);
        }
    }
}
