package com.code.service.impl;

import com.code.custom_exception.BusinessException;
import com.code.entity.Employee;
import com.code.repository.EmployeeRepository;
import com.code.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        try {
            if (employee.getName().isEmpty() || employee.getName().length() == 0) {
                throw new BusinessException("601 ", "Please send proper name, It blank ");
            }
            Employee savedEmployee = employeeRepository.save(employee);
            return savedEmployee;
        } catch (IllegalArgumentException e) {
            throw new BusinessException("602", "given employee is null" + e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("603", "Something wnt wrong in Service layer" + e.getMessage());
        }
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    @Override
    public void deleteByEmpId(Long empId) {
        employeeRepository.deleteById(empId);

    }
}
