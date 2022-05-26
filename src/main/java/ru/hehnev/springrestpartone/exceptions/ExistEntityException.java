package ru.hehnev.springrestpartone.exceptions;

public class ExistEntityException extends RuntimeException {
    public ExistEntityException(String message) {
        super(message);
    }
}
