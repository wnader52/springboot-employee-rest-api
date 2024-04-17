# Spring Boot Employee REST API


This Employee Rest API is written in Java 17 and Spring Boot 3.2.3 (latest version).
This Employee Rest API is written by Waseem Nader and is publicly available in Github.
The way to build and run this is: 
- Download the source code and execute Maven update on it (pom.xml) so that all the necessary libraries to build this API get pulled down to your local environment. Then do a build on it running Maven init, and compile.
- Once correctly compiled and built, run the Application and access the follow URL:
- http://localhost:8082/v3/api-docs
- http://localhost:8082/v3/api-docs/controller-api
- http://localhost:8082/swagger-ui/index.html
- Execute the initializeEmployees() call first so that data is generated and placed in memory. Then subsequently run all the other calls.
- This API allows you to do the HTTP GET, POST, PUT, DELETE CRUD Operations.
- Call the initializeDB() method in the EmployeeController to create some dummy Employee objects
  and save them to the database.

- Author: Waseem Nader

## Run Spring Boot Application
See the http://localhost:8082/swagger-ui/index.html for usage and POSTMAN scripts. Run the calls in this order so that data is first created and then other operations are performed on it.
- Initialize employees ../api/initializeEmployees  
- Retrieve all employees ../api/employees
- Retrieve employee by Id ../api/employees/1
- Create employee ../api/employees
- Update employee ../api/employees
- Delete employee ../api/employees/1
```
mvn spring-boot:clean
mvn spring-boot:compile
mvn spring-boot:run
```

## Run Spring Boot ApplicationTests
The tests can be executed in IJ IDE by simply clicking on the ApplicationTests and Debug 'ApplicationTests'
There is a total of 6 test cases proving unit test coverage.
