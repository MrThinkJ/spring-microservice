package com.mrthinkj.departmentservice.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "department_name", nullable = false)
    private String departmentName;
    @Column(name = "department_description", nullable = false)
    private String departmentDescription;
    @Column(name = "department_code", nullable = false)
    private String departmentCode;
}
