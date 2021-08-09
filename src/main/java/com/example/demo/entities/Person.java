package com.example.demo.entities;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Persons", schema = "public")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Parameter(description = "Индетификатор сотрудника")
    private Long id;

    @Column(name = "first_name")
    @Parameter(description = "Имя сотрудника")
    private String firstName;

    @Column(name = "last_name")
    @Parameter(description = "Фамилия сотрудника")
    private String lastName;

    @Column(name = "patronymic")
    @Parameter(description = "Отчество сотрудника")
    private String patronymic;

    @Column(name = "date")
    @Parameter(description = "Дата рождения сотрудника")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    private Department department;
}
