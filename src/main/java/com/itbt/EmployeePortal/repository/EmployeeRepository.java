package com.itbt.EmployeePortal.repository;

import com.itbt.EmployeePortal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstNameOrLastNameOrEmail(String firstName, String lastName, String email);

    Optional<Employee> findByUserName(String username);

}
