package com.mrthinkj.organizationservice.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "organizations")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "organization_name", nullable = false)
    private String organizationName;
    private String organizationDescription;
    @Column(name = "organization_code", nullable = false, unique = true)
    private String organizationCode;
    @CreationTimestamp
    private LocalDateTime createdDate;
}
