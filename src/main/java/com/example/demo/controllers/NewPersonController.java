package com.example.demo.controllers;

import com.example.demo.entities.NewPerson;
import com.example.demo.services.interfaces.NewPersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/persons")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Сотрудники", description = "Взаимодействие с сотрудниками")
public class NewPersonController {

    private final NewPersonService newPersonService;

    @PostMapping("/add")
    @Operation(summary = "Добавить сотрудника", description = "Позволяет добавить сотрудника")
    public NewPerson sendAndSave(@RequestBody NewPerson newPerson) {
        newPersonService.addNewPerson(newPerson);
        newPersonService.send(newPerson);
        return newPerson;
    }

}
