package com.repository;

import com.bean.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("UPDATE Employee e SET e.salary = ?1 WHERE e.id = ?2 THEN true ELSE false")
    @Modifying
    @Transactional
    boolean reviseSalary(Integer salary, Integer id);

    @Query("SELECT * FROM Employee e WHERE e.designation = ?1")
    Iterable<Employee> findEmployeesByDesignation(String designation);

    @Query("DELETE FROM Employee e WHERE e.id = ?1 THEN true ELSE false")
    @Modifying
    boolean deleteEmployeeRecord(Integer id);
}
