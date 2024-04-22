package com.sample.spring.employee.restapi.controller;

import java.util.List;
import java.util.Optional;

import com.sample.spring.employee.restapi.entity.Employee;
import com.sample.spring.employee.restapi.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.employee.restapi.service.EmployeeService;

/**
 * The type Error response.
 *
 * @author Waseem Nader
 */
@RestController
@RequestMapping("/api")
// Class
public class EmployeeController {

  @Autowired private EmployeeService employeeService;


  /**
   * Fetch all the employees
   * @return list<Employee>
   */
  @GetMapping("/employees")
  public List<Employee> fetchEmployees() {
    return employeeService.fetchEmployees();
  }


  /**
   * Fetch employee by Id
   * @param employeeId
   * @return
   */
  @GetMapping(value = "/employees/{employeeId}", produces = "application/json")
  public ResponseEntity<Employee> fetchEmployeeById(@PathVariable(required=true) Long employeeId) throws ResourceNotFoundException {
    Employee employee = employeeService.fetchEmployeeById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee (" + employeeId + ") not found"));
    return ResponseEntity.ok().body(employee);
  }

  /**
   * Initialize the database with dummy employee objects
   * @param initFlag
   * @return void
   */
  // @PostMapping("/employees/initializeEmployees/{flag}")
  // public String initializeDB(@PathVariable("flag") boolean initFlag)
  // {
  //   employeeService.initializeDB(initFlag);
  //   return "Database initialized with data successfully";
  // }

  /**
   * Add an employee object to the database
   * @param employee
   * @return Employee
   */
  @PostMapping("/employees")
  public Employee createEmployee(@Valid @RequestBody Employee employee) throws Exception {
    Employee createdEmployee = employeeService.createEmployee(employee);
    if(createdEmployee == null){
      throw new Exception("Failed to create employee.");
    }
    return createdEmployee;
  }

  /***
   * Update an Employee object
   * @param employee
   * @param employeeId
   * @return
   */
  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable("id") Long employeeId)
          throws ResourceNotFoundException {
    Employee result = employeeService.updateEmployee(employee, employeeId);
    if(result == null){
      throw new ResourceNotFoundException("Employee (" + employeeId + ") not found");
    }
    return ResponseEntity.ok().body(result);
  }

  /**
   * Delete an employee object from the database
   * @param employeeId
   * @return String
   */
  @DeleteMapping("/employees/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) throws  ResourceNotFoundException {
    Employee employee = employeeService.fetchEmployeeById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee (" + employeeId + ") not found"));
    employeeService.deleteEmployee(employeeId);
    String result = "Employee (" + employeeId + ") deleted successfully";
    return ResponseEntity.ok().body(result);
  }
}