package com.sample.spring.employee.restapi.service;

import com.sample.spring.employee.restapi.entity.Employee;

import java.util.List;
import java.util.Optional;

// Interface
public interface IEmployeeService {

  // Read operation
  List<Employee> fetchEmployees();

  Optional<Employee> fetchEmployeeById(Long employeeId);

  // Save operation
  Employee createEmployee(Employee employee);

  // Update operation
  Employee updateEmployee(Employee employee, Long departmentId);

  // Delete operation
  void deleteEmployee(Long employeeId);

  void initializeDB(boolean flag);
}