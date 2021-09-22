package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonIdNotFoundException extends RuntimeException {
    public PersonIdNotFoundException(Long id) {
        super("Сотрудника с id = " + id + " не существует!");
    }
}
