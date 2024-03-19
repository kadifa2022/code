package com.code.controller;

import com.code.custom_exception.BusinessException;
import com.code.custom_exception.ControllerException;
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
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        try{
        Employee employeeSaved = employeeService.addEmployee(employee);
        return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
    }catch (BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
        return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
    }catch (Exception e) {
            ControllerException ce = new ControllerException(" 611", "Something went wrong in controller");
       return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

        }
        }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> listOfAllEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<List<Employee>>(listOfAllEmployees, HttpStatus.OK);

    }

    @GetMapping("/emp/{empid}")
    public ResponseEntity<?> getEmployeeById(@PathVariable ("empid") Long employeeId){
        try {
            Employee empRetrieved = employeeService.getEmployeeById(employeeId);
            return new ResponseEntity<Employee>(empRetrieved, HttpStatus.OK);
        }catch (BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("612", "Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST );
        }

    }
    @DeleteMapping("/delete/{empid}")
    public ResponseEntity<Void> deleteEmpById(@PathVariable ("empid") Long empId){
        employeeService.deleteByEmpId(empId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);

    }
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CREATED);

    }













}
