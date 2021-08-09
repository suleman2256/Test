package com.example.demo.entities;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Departments", schema = "public")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
}
