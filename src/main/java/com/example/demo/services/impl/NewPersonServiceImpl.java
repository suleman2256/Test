package com.example.demo.services.impl;

import com.example.demo.entities.NewPerson;
import com.example.demo.repositories.NewPersonRepository;
import com.example.demo.services.interfaces.NewPersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewPersonServiceImpl implements NewPersonService {

    private final KafkaTemplate<Long, NewPerson> kafkaNewPersonTemplate;
    private final ObjectMapper objectMapper;
    private final NewPersonRepository newPersonRepository;

    public NewPersonServiceImpl(KafkaTemplate<Long, NewPerson> kafkaNewPersonTemplate, ObjectMapper objectMapper, NewPersonRepository newPersonRepository) {
        this.kafkaNewPersonTemplate = kafkaNewPersonTemplate;
        this.objectMapper = objectMapper;
        this.newPersonRepository = newPersonRepository;
    }

    @Override
    public void addNewPerson(NewPerson newPerson) {
        newPersonRepository.save(newPerson);
    }

    @Override
    public void send(NewPerson newPerson) {
        log.info("<= sending {}", writeValueAsString(newPerson));
        kafkaNewPersonTemplate.send("server.new.person", newPerson);
    }

    @Override
    @KafkaListener(id = "NewPerson", topics = {"server.new.person"}, containerFactory = "singleFactory")
    public void consume(NewPerson newPerson) {
        log.info("=> consumed {}", writeValueAsString(newPerson));
    }

    private String writeValueAsString(NewPerson newPerson) {
        try {
            return objectMapper.writeValueAsString(newPerson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + newPerson.toString());
        }
    }
}
