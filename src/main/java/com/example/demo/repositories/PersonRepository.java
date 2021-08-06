package com.example.demo.repositories;

import com.example.demo.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT l.lastName FROM Person l WHERE l.lastName LIKE 'C%'")
    List<Person> findPersonByLastName(String lastName);

}
