package com.example.demo.entities;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "Persons", schema = "public")
public class NewPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_id_seq")
    @SequenceGenerator(name = "entity_id_seq", sequenceName = "global_id_sequence", allocationSize = 1)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
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

    @OneToOne
    private Department department;
}

