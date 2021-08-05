package com.example.demo.services.interfaces;

import com.example.demo.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {


    List<Person> findPersonAll();

}
