package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentIdNotFound extends Exception {
    public DepartmentIdNotFound(Long id) {
        super("Подразделения с id = " + id + " не существует!");
    }
}
