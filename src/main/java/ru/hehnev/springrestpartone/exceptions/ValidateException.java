package ru.hehnev.springrestpartone.exceptions;

import java.util.List;

public class ValidateException extends RuntimeException{
    private final List<String> errors;
    public ValidateException(List<String> errors) {
        super(String.join(", ", errors));
        this.errors = errors;
    }
}
