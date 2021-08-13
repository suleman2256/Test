package com.example.demo.repositories;


import com.example.demo.entities.NewPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewPersonRepository extends JpaRepository<NewPerson, Long> {
}
