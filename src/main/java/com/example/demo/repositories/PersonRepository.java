package com.example.demo.repositories;

import com.example.demo.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findPersonByLastName(String lastName);

    Optional<Person> findPersonByFirstName(String firstName);

    @Query("SELECT p FROM Person p WHERE department.id = 1")
    List<Person> findPersonByDepartmentOne();

    @Query("SELECT p FROM Person p WHERE department.id = 2")
    List<Person> findPersonByDepartmentTwo();

    @Query("SELECT p FROM Person p WHERE department.id = 3")
    List<Person> findPersonByDepartmentThree();

    @Query("SELECT p from Person p where department.id = 1 or department.id = 2 or  department.id = 3")
    List<Person> findPersonByDepartmentsAll();

}
