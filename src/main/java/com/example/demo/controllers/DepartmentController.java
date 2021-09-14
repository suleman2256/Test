package com.example.demo.controllers;

import com.example.demo.entities.Department;
import com.example.demo.services.interfaces.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/", consumes = {MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Подразделение", description = "Взаимодействие с подразделениями")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("departments")
    @Operation(summary = "Получить подразделения", description = "Позволяет вывести все подразделения")
    public List<Department> allDepartments() {
        return departmentService.findDepartmentAll();
    }

    @Operation(summary = "Получить подразделение", description = "Позволяет вывести конкретное подразделение по его id")
    @GetMapping("departments/{id}")
    public Department findDepartmentsById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @Operation(summary = "Получить расчёт месячных расходов", description = "Позволяет вывести расчёт месячных расчётов по всем или каждому подразделению")
    @RequestMapping(value = "departments/sum/{id}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public String getSumMonthSalary(@RequestParam(required = false) Long id) {
        return departmentService.getSumMonthSalary(id);
    }

}
