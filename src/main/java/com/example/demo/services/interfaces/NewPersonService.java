package com.example.demo.services.interfaces;

import com.example.demo.entities.NewPerson;
import org.springframework.stereotype.Service;

@Service
public interface NewPersonService {

    void addNewPerson(NewPerson newPerson);

    void send(NewPerson newPerson);

    void consume(NewPerson newPerson);
}
