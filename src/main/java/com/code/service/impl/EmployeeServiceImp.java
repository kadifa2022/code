package com.code.service.impl;

import com.code.custom_exception.BusinessException;
import com.code.entity.Employee;
import com.code.repository.EmployeeRepository;
import com.code.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        //try  // we should never put try for validation
        if (employee.getName().isEmpty() || employee.getName().length() == 0) {
                throw new BusinessException("601 ", "Please send proper name, It blank ");
        }
        try {  // we should always use try with the repository code
            Employee savedEmployee = employeeRepository.save(employee);
            return savedEmployee;
        } catch (IllegalArgumentException e) {
            throw new BusinessException("602", "given employee is null" + e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("603", "Something went wrong in Service layer saving the employees" + e.getMessage());
        }
    }
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> empList = null;
        try {
            empList = employeeRepository.findAll();
        } catch (Exception e) {
            throw new BusinessException("605", "Something went wrong in Service layers while fetching the all employees " + e.getMessage());
        }
        if (empList.isEmpty())
            throw new BusinessException(" 604", "List is completely empty, we have nothing to return");
            return empList;
        }


    @Override
    public Employee getEmployeeById(Long employeeId) {
        try{
            return employeeRepository.findById(employeeId).get();
    }catch (IllegalArgumentException e){
            throw new BusinessException("606", "given employee id is null, please send some id to be searched" + e.getMessage());
        }
        catch (NoSuchElementException e) {
            throw new BusinessException("607", "given employee id does not exist in DB " + e.getMessage());
        }
        }

    @Override
    public void deleteByEmpId(Long empId) {
        try{
        employeeRepository.deleteById(empId);
        }catch (IllegalArgumentException e) {
            throw new BusinessException("608", "given employee id is null, please send me some id to be searched " + e.getMessage());
        }
        }
}
