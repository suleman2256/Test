package com.example.demo.repositories;

import com.example.demo.entities.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /*@Query("SELECT l.lastName FROM Person l WHERE l.lastName LIKE CONCAT('%)',:lastName,'%')")
    List<Person> findByLastName(@Param("lastName") String lastName);*/

    List<Person> findByLastName(String lastName);

}
