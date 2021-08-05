package com.example.demo.controllers;

import com.example.demo.entities.Department;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.services.interfaces.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments/all")
    public List<Department> allDepartments() {
        return departmentService.findDepartmentAll();
    }

}
