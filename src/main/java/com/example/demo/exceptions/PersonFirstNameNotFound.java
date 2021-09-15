package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonFirstNameNotFound extends RuntimeException {
    public PersonFirstNameNotFound (String firstName) {
        super("Сотрудника с именем = " + firstName + " не существует!");
    }
}
