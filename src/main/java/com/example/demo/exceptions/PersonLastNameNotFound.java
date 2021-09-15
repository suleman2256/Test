package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonLastNameNotFound extends RuntimeException {
    public PersonLastNameNotFound(String lastName) {
        super("Сотрудника с фамилией = " + lastName + " не существует!");
    }
}
