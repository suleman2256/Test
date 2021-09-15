package com.example.demo.services.interfaces;

import com.example.demo.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {


    List<Person> findAllPerson();

    Person findPersonByLastName(String lastName);

    Person findById(Long id);

    Person findPersonByFirstName(String firstName);

    void delete(Long id);
}
