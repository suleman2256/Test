package com.example.demo.controllers;

import com.example.demo.entities.Person;
import com.example.demo.services.interfaces.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Сотрудники", description = "Взаимодействие с сотрудниками")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/persons")
    @Operation(summary = "Все сотрудники", description = "Позволяет вывести всех сотрудников")
    public List<Person> findAllPerson() {
        return personService.findAllPerson();
    }

    @GetMapping("/persons/{id}")
    @Operation(summary = "Получение сотрудника", description = "Позволяет вывести сотрудника по его id")
    public Optional<Person> findPersonById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @GetMapping("/persons/lastname")
    @Operation(summary = "Фильтрация по фамилии сотрудника", description = "Позволяет вывести сотрудника по фамильно")
    public List<Person> findPersonByLastName(@PathVariable String lastName) {
        return personService.findPersonByLastName(lastName);
    }


}
