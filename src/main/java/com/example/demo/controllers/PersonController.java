package com.example.demo.controllers;

import com.example.demo.entities.Department;
import com.example.demo.entities.Person;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons/all")
    public List<Person> allPerson() {
        return personService.findPersonAll();
    }

}
