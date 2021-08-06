package com.example.demo.repositories;

import com.example.demo.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    //@Query("SELECT l.lastName FROM Person l WHERE l.lastName LIKE 'С%'")
    Person findPersonByLastName(String lastName);

    //@Query("SELECT firstName FROM Person WHERE upper(substring(firstName,1,1)) and substring(firstName,2,length(firstName)-1)" )
    Person findPersonByFirstName(String firstName);


}
