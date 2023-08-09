package com.service;

import com.bean.Employee;
import com.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAllEmployees()
    {
        Iterable<Employee> iterator = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        iterator.forEach(employees::add);
        if(employees.isEmpty()){
            return null;
        }
        return employees;
    }
    @Override

    public int addEmployee(Employee employee) {
        Employee createdEmployee = employeeRepository.save(employee);
        return createdEmployee.getId();
    }
    @Override

    public Employee findEmployeeById(Integer employeeId) {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        return optional.orElse(null);
    }
    @Override
    public List<Employee> findEmployeesByDesignation(String designation)
    {
        Iterable<Employee> iterator = employeeRepository.findEmployeesByDesignation(designation);
        List<Employee> employees = new ArrayList<>();
        iterator.forEach(employees::add);
        if(employees.isEmpty()){
            return null;
        }
        return employees;
    }
    @Override
    public boolean reviseSalary(Integer employeeId, Integer salary){
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        return optional.isPresent() && employeeRepository.reviseSalary(salary, employeeId);

    }
    @Override
    public boolean removeEmployeeRecord(Integer employeeId){
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        return optional.isPresent() && employeeRepository.deleteEmployeeRecord(employeeId);

    }
}
