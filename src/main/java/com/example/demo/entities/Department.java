package com.example.demo.entities;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Departments", schema = "public")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_id_seq")
    @SequenceGenerator(name = "entity_id_seq", sequenceName = "global_id_sequence", allocationSize = 1)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @Parameter(description = "Идентификатор подразделения")
    private Long id;

    @Column(name = "full_name")
    @Parameter(description = "Полное наименование подразделения")
    private String fullName;

    @Column(name = "short_name")
    @Parameter(description = "Короткое наименование подразделения")
    private String shortName;

    @Column(name = "phone_number")
    @Parameter(description = "Контактные данные подразделения")
    private String phoneNumber;

    private Integer result;
}
