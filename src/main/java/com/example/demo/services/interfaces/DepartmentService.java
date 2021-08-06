package com.example.demo.services.interfaces;

import com.example.demo.entities.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    List<Department> findDepartmentAll();

    Department findById(Long id);
}
