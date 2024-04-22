package com.sample.spring.employee.restapi.service;

import java.util.*;

import com.sample.spring.employee.restapi.entity.Employee;
import com.sample.spring.employee.restapi.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The EmployeeService class that's instantiated via Dependency Injection
 * using the Spring Boot Framework
 *
 * @author Waseem Nader
 */
@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * This method fetches all the employees
     * @return
     */
    @Override
    public List<Employee> fetchEmployees()
    {
        return (List<Employee>)
                employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> fetchEmployeeById(Long employeeId){
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Employee createEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    // Update operation
    @Override
    public Employee updateEmployee(Employee employee, Long employeeId)
    {
        Employee depDB = employeeRepository.findById(employeeId).get();
        if (Objects.nonNull(employee.getFirstName()) && !"".equalsIgnoreCase(employee.getFirstName())) {
            depDB.setFirstName(employee.getFirstName());
        }
        if (Objects.nonNull(employee.getLastName()) && !"".equalsIgnoreCase(employee.getLastName())) {
            depDB.setLastName(employee.getLastName());
        }
        if (Objects.nonNull(employee.getEmailAddress()) && !"".equalsIgnoreCase(employee.getEmailAddress())) {
            depDB.setEmailAddress(employee.getEmailAddress());
        }
        if (Objects.nonNull(employee.getPhone()) && !"".equalsIgnoreCase(employee.getPhone())) {
            depDB.setPhone(employee.getPhone());
        }
        if (Objects.nonNull(employee.getBirthDate())) {
            depDB.setBirthDate(employee.getBirthDate());
        }
        if (Objects.nonNull(employee.getJobTitle()) && !"".equalsIgnoreCase(employee.getJobTitle())) {
            depDB.setJobTitle(employee.getJobTitle());
        }
        if (Objects.nonNull(employee.getDepartment()) && !"".equalsIgnoreCase(employee.getDepartment())) {
            depDB.setDepartment(employee.getDepartment());
        }
        if (Objects.nonNull(employee.getLocation()) && !"".equalsIgnoreCase(employee.getLocation())) {
            depDB.setLocation(employee.getLocation());
        }
        if (Objects.nonNull(employee.getStartDate())) {
            depDB.setStartDate(employee.getStartDate());
        }
        if (Objects.nonNull(employee.getManagerReporting()) && !"".equalsIgnoreCase(employee.getManagerReporting())) {
            depDB.setManagerReporting(employee.getManagerReporting());
        }
        return employeeRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void deleteEmployee(Long employeeId)
    {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    @PostConstruct
    public void initializeDB(){
        Employee employee = new Employee();
        employee.setFirstName("Jim");
        employee.setLastName("Bob");
        employee.setEmailAddress("jim.bob@sampleapi.com");
        employee.setLocation("Dover, NH");
        employee.setDepartment("IT Department");
        employee.setPhone("603-123-3232");
        employee.setStartDate("2014-01-02");
        employee.setBirthDate("2000-01-03");
        employee.setJobTitle("Software Developer");
        employee.setManagerReporting("Larry Johnson");
        employeeRepository.save(employee);

        employee = new Employee();
        employee.setFirstName("Mark");
        employee.setLastName("Baringer");
        employee.setEmailAddress("mark.baringer@sampleapi.com");
        employee.setLocation("Durham, NH");
        employee.setDepartment("HR Department");
        employee.setPhone("603-232-3232");
        employee.setStartDate("2023-09-10");
        employee.setBirthDate("2001-09-29");
        employee.setJobTitle("HR Director");
        employee.setManagerReporting("Larry Johnson");
        employeeRepository.save(employee);

        employee = new Employee();
        employee.setFirstName("Tim");
        employee.setLastName("Cane");
        employee.setEmailAddress("tim.cane@sampleapi.com");
        employee.setLocation("Rochester, NH");
        employee.setDepartment("Research & Development Department");
        employee.setPhone("603-783-8812");
        employee.setStartDate("1998-01-02");
        employee.setBirthDate("2024-03-3");
        employee.setJobTitle("Software Tester");
        employee.setManagerReporting("Larry Johnson");
        employeeRepository.save(employee);

        employee = new Employee();
        employee.setFirstName("Mike");
        employee.setLastName("Darinson");
        employee.setEmailAddress("mike.darinson@sampleapi.com");
        employee.setLocation("Manchester, NH");
        employee.setDepartment("Research & Development Department");
        employee.setPhone("603-783-9090");
        employee.setStartDate("2023-10-10");
        employee.setBirthDate("2023-08-08");
        employee.setJobTitle("Software Architect");
        employee.setManagerReporting("Larry Johnson");
        employeeRepository.save(employee);

        employee = new Employee();
        employee.setFirstName("Derrick");
        employee.setLastName("Jeeter");
        employee.setEmailAddress("derrick.jeeter@sampleapi.com");
        employee.setLocation("Manchester, NH");
        employee.setDepartment("Research & Development Department");
        employee.setPhone("603-876-9090");
        employee.setStartDate("2023-10-11");
        employee.setBirthDate("2000-06-03");
        employee.setJobTitle("Software Architect");
        employee.setManagerReporting("Larry Johnson");
        employeeRepository.save(employee);

        employee = new Employee();
        employee.setFirstName("Cara");
        employee.setLastName("Stephenson");
        employee.setEmailAddress("Cara.Stephenson@sampleapi.com");
        employee.setLocation("Durham, NH");
        employee.setDepartment("Sales & Marketing");
        employee.setPhone("603-453-5656");
        employee.setStartDate("2024-01-11");
        employee.setBirthDate("2000-08-03");
        employee.setJobTitle("Sales Agent");
        employee.setManagerReporting("Ken Stylind");
        employeeRepository.save(employee);
    }
}

