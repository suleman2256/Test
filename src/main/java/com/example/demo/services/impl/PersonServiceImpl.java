package com.example.demo.services.impl;

import com.example.demo.entities.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.interfaces.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person findPersonByLastName(String lastName) {
        return personRepository.findPersonByLastName(lastName);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public Person findPersonByFirstName(String firstName) {
        return personRepository.findPersonByFirstName(firstName);
    }

}
