package com.example.demo.controllers;

import com.example.demo.entities.NewPerson;
import com.example.demo.entities.Person;
import com.example.demo.services.interfaces.NewPersonService;
import com.example.demo.services.interfaces.PersonService;
import com.example.demo.services.interfaces.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/", consumes = {MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Сотрудники", description = "Взаимодействие с сотрудниками")
public class PersonController {

    private final PersonService personService;
    private final NewPersonService newPersonService;
    private final ReportService reportService;

    @PostMapping(value = "persons/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Добавить сотрудника", description = "Позволяет добавить сотрудника")
    public NewPerson sendAndSave(@RequestBody NewPerson newPerson) {
        newPersonService.addNewPerson(newPerson);
        newPersonService.send(newPerson);
        return newPerson;
    }

    @PostMapping("persons/delete/{id}")
    @Operation(summary = "Удалить сотрудника", description = "Позволяет удалить сотрудника по его id")
    public Person delete(@PathVariable Long id) {
        Person person = personService.findById(id);
        personService.delete(id);
        return person;
    }

    @RequestMapping(value = "persons", method = RequestMethod.GET)
    @Operation(summary = "Все сотрудники", description = "Позволяет вывести всех сотрудников")
    public List<Person> findAllPerson() {
        return personService.findAllPerson();
    }

    @RequestMapping(value = "persons/{id}", method = RequestMethod.GET)
    @Operation(summary = "Получение сотрудника", description = "Позволяет вывести сотрудника по его id")
    public Person findPersonById(@RequestParam("id") Long id) {
        return personService.findById(id);
    }

    @RequestMapping(value = "persons/findbylastname", method = RequestMethod.GET)
    @Operation(summary = "Поиск сотрудника по фамилии", description = "Позволяет вывести сотрудника по фамилии")
    public Person findPersonByLastName(@RequestParam("lastName") String lastName) {
        return personService.findPersonByLastName(lastName);
    }

    @RequestMapping(value = "persons/findbyfirstname", method = RequestMethod.GET)
    @Operation(summary = "Поиск сотрудника по имени", description = "Позволяет вывести сотрудника по имени")
    public Person findPersonByFirstName(@RequestParam("firstName") String firstName) {
        return personService.findPersonByFirstName(firstName);
    }

    @RequestMapping(value = "persons/pdf", method = RequestMethod.GET)
    @Operation(summary = "Список сотрудников в PDF формате", description = "Позволяет получить список сотрудников в PDF формате")
    public ResponseEntity<Resource> generateReportPDF() throws JRException, IOException {
        return reportService.exportReportPdf();
    }

    @RequestMapping(value = "persons/xls", method = RequestMethod.GET)
    @Operation(summary = "Список сотрудников в XLS формате", description = "Позволяет получить список сотрудников в XLS формате")
    public ResponseEntity<Resource> generateReportXls() throws JRException, IOException {
        return reportService.exportReportXls();
    }
}
