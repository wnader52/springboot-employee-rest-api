package com.sample.spring.employee.restapi.repository;

import com.sample.spring.employee.restapi.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Annotation
@Repository

// Interface extending CrudRepository
public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {
}
