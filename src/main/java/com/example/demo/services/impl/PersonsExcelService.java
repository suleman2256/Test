package com.example.demo.services.impl;

import com.example.demo.entities.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.util.PersonsExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class PersonsExcelService {

    @Autowired
    PersonRepository personRepository;

    public ByteArrayInputStream load() {
        List<Person> personList = personRepository.findAll();

        ByteArrayInputStream byteArrayInputStream = PersonsExcel.personsToExcel(personList);
        return byteArrayInputStream;
    }
}
