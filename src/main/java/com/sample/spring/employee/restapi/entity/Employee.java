package com.sample.spring.employee.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Employee")

// Class
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String firstName;

    @Size(max = 32)
    @Column(nullable = false)
    private String lastName;

    @Size(max = 32)
    @Column(nullable = false)
    private String emailAddress;

    @Size(max = 32)
    @Column(nullable = false)
    private String phone;
    
    private String birthDate;
    private String jobTitle;
    private String department;
    private String location;
    private String startDate;
    private String managerReporting;
}