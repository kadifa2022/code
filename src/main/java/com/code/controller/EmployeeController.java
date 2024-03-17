package com.code.controller;

import com.code.entity.Employee;

import com.code.service.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/code")
public class EmployeeController {


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    private final EmployeeService employeeService;


    @PostMapping("/save") //@RequestBody should be bound to the body of the web request
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee employeeSaved = employeeService.addEmployee(employee);
        return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> listOfAllEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<List<Employee>>(listOfAllEmployees, HttpStatus.OK);

    }


}
