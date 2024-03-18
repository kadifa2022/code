package com.code.service;

import com.code.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId);

    void deleteByEmpId(Long empId);
}
