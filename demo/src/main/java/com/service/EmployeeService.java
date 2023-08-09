package com.service;

import com.bean.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> findAllEmployees();
    public int addEmployee(Employee employee);
    public Employee findEmployeeById(Integer employeeId);
    public List<Employee> findEmployeesByDesignation(String designation);
    public boolean reviseSalary(Integer employeeId, Integer salary);
    public boolean removeEmployeeRecord(Integer employeeId);
}
