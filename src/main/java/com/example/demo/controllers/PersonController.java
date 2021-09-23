package com.example.demo.controllers;

import com.example.demo.entities.NewPerson;
import com.example.demo.entities.Person;
import com.example.demo.services.impl.PersonsExcelService;
import com.example.demo.services.interfaces.NewPersonService;
import com.example.demo.services.interfaces.PersonService;
import com.example.demo.util.PersonsPDF;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/", consumes = {MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Сотрудники", description = "Взаимодействие с сотрудниками")
public class PersonController {

    private final PersonService personService;
    private final NewPersonService newPersonService;
    private final PersonsExcelService personsExcelService;

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

    @RequestMapping(value = "persons/pdf", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Список сотрудников в PDF", description = "Позволяет получить список сотрудникв в PDF формате")
    public void getPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Persons_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Person> personList = personService.findAllPerson();

        PersonsPDF personsPDF = new PersonsPDF(personList);
        personsPDF.export(response);
    }

    @RequestMapping(value = "persons/xls", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Список сотрудников в Excel", description = "Позволяет получить список сотрудникв в Excel формате")
    public ResponseEntity<Resource> getXls() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        InputStreamResource file = new InputStreamResource(personsExcelService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Persons_" + currentDateTime + ".xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
