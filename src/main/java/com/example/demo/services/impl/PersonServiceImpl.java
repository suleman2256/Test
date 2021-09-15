package com.example.demo.services.impl;

import com.example.demo.entities.Person;
import com.example.demo.exceptions.PersonFirstNameNotFound;
import com.example.demo.exceptions.PersonIdNotFound;
import com.example.demo.exceptions.PersonLastNameNotFound;
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
        return personRepository.findPersonByLastName(lastName).orElseThrow(() -> new PersonLastNameNotFound(lastName));
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonIdNotFound(id));
    }

    @Override
    public Person findPersonByFirstName(String firstName) {
        return personRepository.findPersonByFirstName(firstName).orElseThrow(() -> new PersonFirstNameNotFound(firstName));
    }

    @Override
    public void delete(Long id) {
        personRepository.delete(findById(id));
    }

}
