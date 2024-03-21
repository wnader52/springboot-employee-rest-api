package com.sample.spring.employee.restapi;

import com.sample.spring.employee.restapi.controller.EmployeeController;
import com.sample.spring.employee.restapi.entity.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private EmployeeController employeeController;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@DisplayName("Initializing data...")
	@BeforeAll
	public void initialize(){
		System.out.println("Adding data...");
		long employeeId = 1;
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setEmailAddress("admin@gmail.com");
		employee.setFirstName("Mike");
		employee.setLastName("Hinderson");
		employee.setPhone("871-908-2020");
		employee.setLocation("Dover, NH");
		employee.setDepartment("HR Department");
		employee.setJobTitle("Director of HR");
		employee.setBirthDate(new Date(1991, 04, 10, 0, 0));
		employee.setStartDate(new Date(2024, 02, 02, 0, 0));
		employee.setManagerReporting("Steve Weindelly");

		ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/employees", employee, Employee.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());

		employeeId = 2;
		employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setEmailAddress("manager@gmail.com");
		employee.setFirstName("Harry");
		employee.setLastName("Johnson");
		employee.setPhone("207-908-2020");
		employee.setLocation("Rochester, NH");
		employee.setDepartment("IT Department");
		employee.setJobTitle("Solutions Architect");
		employee.setBirthDate(new Date(1990, 02, 02, 0, 0));
		employee.setStartDate(new Date(2023, 06, 01, 0, 0));
		employee.setManagerReporting("Danny Bear");

		postResponse = restTemplate.postForEntity(getRootUrl() + "/api/employees", employee, Employee.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());

		employeeId = 3;
		employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setEmailAddress("employee@gmail.com");
		employee.setFirstName("Cara");
		employee.setLastName("Stephenson");
		employee.setPhone("207-900-2020");
		employee.setLocation("Nashua, NH");
		employee.setDepartment("IT Department");
		employee.setJobTitle("Software Developer");
		employee.setBirthDate(new Date(1994, 02, 02, 0, 0));
		employee.setStartDate(new Date(2023, 07, 10, 0, 0));
		employee.setManagerReporting("Danny Bear");

		postResponse = restTemplate.postForEntity(getRootUrl() + "/api/employees", employee, Employee.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@DisplayName("Test 1- testing employeeController")
	@Test
	public void contextLoads() {
		assertNotNull(employeeController);
	}

	@DisplayName("Test 2 - testing getEmployeeById")
	@Test
	public void testGetEmployeeById() {
		int id = 2;
		Employee employee = restTemplate.getForObject(getRootUrl() + "/api/employees/" + id, Employee.class);
		assertNotNull(employee);
		System.out.println("Retrieved Employee: " + employee.getFirstName() + " " + employee.getLastName());
	}

	@DisplayName("Test 3 - testing createEmployee")
	@Test
	public void testCreateEmployee() {
		long employeeId = 4;
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setEmailAddress("contractor@gmail.com");
		employee.setFirstName("Sundeep");
		employee.setLastName("Gupta");
		employee.setPhone("734-123-2022");
		employee.setLocation("Manassas, VA");
		employee.setDepartment("IT Department");
		employee.setJobTitle("Software Tester");
		employee.setBirthDate(new Date(1995, 05, 12, 0, 0));
		employee.setStartDate(new Date(2023, 03, 03, 0, 0));
		employee.setManagerReporting("Shankar Nirayan");

		ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/employees", employee, Employee.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		System.out.println("Created Employee: " + postResponse.getBody());
	}

	@DisplayName("Test 4 - testing getAllEmployees")
	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/employees",
				HttpMethod.GET, entity, String.class);

		assertNotNull(response.getBody());
		System.out.println("Retrieved All Employees");
		System.out.println(response.getBody());
	}

	@DisplayName("Test 5 - testing updateEmployee")
	@Test
	public void testUpdateEmployee() {
		int id = 2;
		Employee employee = restTemplate.getForObject(getRootUrl() + "/api/employees/" + id, Employee.class);
		employee.setFirstName("Jim");
		employee.setLastName("Carry");
		assertNotNull(employee);

		restTemplate.put(getRootUrl() + "/api/employees/" + id, employee);

		Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + "/api/employees/" + id, Employee.class);
		assertNotNull(updatedEmployee);
		System.out.println("Updated Employee: " + updatedEmployee.getFirstName() + " " + updatedEmployee.getLastName());
	}

	@DisplayName("Test 6 - testing deleteEmployee")
	@Test
	public void testDeleteEmployee() {
		int id = 1;
		Employee employee = restTemplate.getForObject(getRootUrl() + "/api/employees/" + id, Employee.class);
		assertNotNull(employee);

		restTemplate.delete(getRootUrl() + "/api/employees/" + id);
		System.out.println("Deleted Employee: " + employee.getFirstName() + " " + employee.getLastName());

		try {
			employee = restTemplate.getForObject(getRootUrl() + "/api/employees/" + id, Employee.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
