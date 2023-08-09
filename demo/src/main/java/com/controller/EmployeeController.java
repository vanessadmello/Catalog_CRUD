package com.controller;

import com.service.EmployeeService;
import com.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

    @Autowired
    private EmployeeService EmployeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAllEmployees()  {
        List<Employee> employees = EmployeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<Employee> findEmployeeById(@RequestParam Integer EmployeeId)  {
        Employee employee = EmployeeService.findEmployeeById(EmployeeId);
        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/Employee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee Employee)  {
        int id = EmployeeService.addEmployee(Employee);
        if(id == -1){
            return new ResponseEntity<>("Failed to Save Record", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Record Saved", HttpStatus.CREATED);
    }

    @PutMapping("/Employee")
    public ResponseEntity<String> reviseSalary(@RequestParam Integer EmployeeId, @RequestParam Integer salary)  {
        if(EmployeeService.reviseSalary(EmployeeId, salary)){
            return new ResponseEntity<>("Update Done", HttpStatus.OK);
        }
        return new ResponseEntity<>("No Such Record", HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/Employee")
    public ResponseEntity<String> removeEmployeeRecord(@RequestParam Integer EmployeeId)  {
        if(EmployeeService.removeEmployeeRecord(EmployeeId)){
            return new ResponseEntity<>("Record Removed", HttpStatus.OK);
        }
        return new ResponseEntity<>("No Such Record", HttpStatus.NO_CONTENT);
    }
}