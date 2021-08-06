package com.example.demo.services.interfaces;

import com.example.demo.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {


    List<Person> findAllPerson();

    List<Person> findPersonByLastName(String lastName);

    Optional<Person> findById(Long id);
}
