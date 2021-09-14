package com.example.demo.services.impl;

import com.example.demo.calculator.Add;
import com.example.demo.calculator.AddResponse;
import com.example.demo.calculator.Calculator;
import com.example.demo.entities.Department;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.interfaces.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final Department department;
    private final DepartmentRepository departmentRepository;
    private final AddResponse addResponse;
    private final Add add;
    private final Calculator calculator;
    private final PersonRepository personRepository;

    public DepartmentServiceImpl(Department department, DepartmentRepository departmentRepository, AddResponse addResponse, Add add, Calculator calculator, PersonRepository personRepository) {
        this.department = department;
        this.departmentRepository = departmentRepository;
        this.addResponse = addResponse;
        this.add = add;
        this.calculator = calculator;
        this.personRepository = personRepository;
    }

    @Override
    public List<Department> findDepartmentAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public String getSumMonthSalary(Long id) {
        addResponse.setAddResult(0);
        String shortName;
        if (id != null) {
            shortName = "подразделения " + departmentRepository.getById(id).getShortName();
        } else {
            shortName = "всех подразделений ";
        }
        if (id == null) {
            if (personRepository.findPersonByDepartmentsAll().size() == 1) {
                id = (long) personRepository.findPersonByDepartmentsAll().size() - 1;
                add.setIntA(personRepository.findPersonByDepartmentsAll().get(Math.toIntExact(id)).getMonthSalary());
                add.setIntB(0);
                addResponse.setAddResult(calculator.getCalculatorSoap().add(add.getIntA(), add.getIntB()));
            } else if (personRepository.findPersonByDepartmentsAll().size() > 1) {
                id = (long) personRepository.findPersonByDepartmentsAll().size() - 1;
                for (id = (long) (personRepository.findPersonByDepartmentsAll().size() - 1); id > -1; id--) {
                    add.setIntA(personRepository.findPersonByDepartmentsAll().get(Math.toIntExact(id)).getMonthSalary());
                    id--;
                    if (id != -1) {
                        add.setIntB(personRepository.findPersonByDepartmentsAll().get(Math.toIntExact(id)).getMonthSalary());
                    } else {
                        add.setIntB(0);
                    }
                    department.setResult(addResponse.getAddResult());
                    addResponse.setAddResult(addResponse.setAddResult(calculator.getCalculatorSoap().add(add.getIntA(), add.getIntB())) + department.getResult());
                }
            }
        } else if (departmentRepository.getById(id).getId().equals(1L)) {
            if (personRepository.findPersonByDepartmentOne().size() == 1) {
                add.setIntA(personRepository.findPersonByDepartmentOne().get(Math.toIntExact(id)).getMonthSalary());
                add.setIntB(0);
                addResponse.setAddResult(calculator.getCalculatorSoap().add(add.getIntA(), add.getIntB()));
            } else if (personRepository.findPersonByDepartmentOne().size() > 1) {
                for (id = (long) (personRepository.findPersonByDepartmentOne().size() - 1); id > -1; id--) {
                    add.setIntA(personRepository.findPersonByDepartmentOne().get(Math.toIntExact(id)).getMonthSalary());
                    id--;
                    if (id != -1) {
                        add.setIntB(personRepository.findPersonByDepartmentOne().get(Math.toIntExact(id)).getMonthSalary());
                    } else {
                        add.setIntB(0);
                    }
                    department.setResult(addResponse.getAddResult());
                    addResponse.setAddResult(addResponse.setAddResult(calculator.getCalculatorSoap().add(add.getIntA(), add.getIntB())) + department.getResult());
                }
            }
        } else if (departmentRepository.getById(id).getId().equals(2L)) {
            if (personRepository.findPersonByDepartmentTwo().size() == 1) {
                id = (long) personRepository.findPersonByDepartmentTwo().size() - 1;
                add.setIntA(personRepository.findPersonByDepartmentTwo().get(Math.toIntExact(id)).getMonthSalary());
                add.setIntB(0);
                addResponse.setAddResult(calculator.getCalculatorSoap().add(add.getIntA(), add.getIntB()));
            } else if (personRepository.findPersonByDepartmentTwo().size()> 1) {
                id = (long) personRepository.findPersonByDepartmentTwo().size() - 1;
                for (id = (long) (personRepository.findPersonByDepartmentTwo().size() - 1); id > -1; id--) {
                    add.setIntA(personRepository.findPersonByDepartmentTwo().get(Math.toIntExact(id)).getMonthSalary());
                    id--;
                    if (id != -1) {
                        add.setIntB(personRepository.findPersonByDepartmentTwo().get(Math.toIntExact(id)).getMonthSalary());
                    } else {
                        add.setIntB(0);
                    }
                    department.setResult(addResponse.getAddResult());
                    addResponse.setAddResult(addResponse.setAddResult(calculator.getCalculatorSoap().add(add.getIntA(), add.getIntB())) + department.getResult());
                }
            }
        } else if (departmentRepository.getById(id).getId().equals(3L)) {
            if (personRepository.findPersonByDepartmentThree().size() == 1) {
                id = (long) personRepository.findPersonByDepartmentThree().size() - 1;
                add.setIntA(personRepository.findPersonByDepartmentThree().get(Math.toIntExact(id)).getMonthSalary());
                add.setIntB(0);
                addResponse.setAddResult(calculator.getCalculatorSoap().add(add.getIntA(), add.getIntB()));
            } else if (personRepository.findPersonByDepartmentThree().size()> 1) {
                id = (long) personRepository.findPersonByDepartmentThree().size() - 1;
                for (id = (long) (personRepository.findPersonByDepartmentThree().size() - 1); id > -1; id--) {
                    add.setIntA(personRepository.findPersonByDepartmentThree().get(Math.toIntExact(id)).getMonthSalary());
                    id--;
                    if (id != -1) {
                        add.setIntB(personRepository.findPersonByDepartmentThree().get(Math.toIntExact(id)).getMonthSalary());
                    } else {
                        add.setIntB(0);
                    }
                    department.setResult(addResponse.getAddResult());
                    addResponse.setAddResult(addResponse.setAddResult(calculator.getCalculatorSoap().add(add.getIntA(), add.getIntB())) + department.getResult());
                }
            }
        }
        return "Расчёт месячных расходов " + shortName + " составил " + String.valueOf(addResponse.getAddResult()) + " рублей";
    }
}

