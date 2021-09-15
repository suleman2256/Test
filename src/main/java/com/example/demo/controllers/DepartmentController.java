package com.example.demo.controllers;

import com.example.demo.entities.Department;
import com.example.demo.exceptions.DepartmentIdNotFound;
import com.example.demo.services.interfaces.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/", consumes = {MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Подразделение", description = "Взаимодействие с подразделениями")
public class DepartmentController {

    private final DepartmentService departmentService;

    @RequestMapping(value = "departments", method = RequestMethod.GET)
    @Operation(summary = "Получить подразделения", description = "Позволяет вывести все подразделения")
    public List<Department> allDepartments() {
        return departmentService.findDepartmentAll();
    }

    @Operation(summary = "Получить подразделение", description = "Позволяет вывести конкретное подразделение по его id")
    @RequestMapping(value = "departments/{id}", method = RequestMethod.GET)
    public Department findDepartmentsById(@RequestParam Long id) throws DepartmentIdNotFound {
        return departmentService.findById(id);
    }

    @Operation(summary = "Получить расчёт месячных расходов", description = "Позволяет вывести расчёт месячных расчётов по всем или каждому подразделению")
    @RequestMapping(value = "departments/sum/{id}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getSumMonthSalary(@RequestParam(required = false) Long id) {
        try {
            String sumMonthSalary = departmentService.getSumMonthSalary(id);
            return ResponseEntity.ok(sumMonthSalary);
        } catch (DepartmentIdNotFound e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}