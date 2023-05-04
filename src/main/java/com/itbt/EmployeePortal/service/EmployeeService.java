package com.itbt.EmployeePortal.service;

import com.itbt.EmployeePortal.dto.EmployeeRequest;
import com.itbt.EmployeePortal.dto.EmployeeResponse;
import com.itbt.EmployeePortal.dto.SearchEmployeeRequest;
import com.itbt.EmployeePortal.model.Employee;
import com.itbt.EmployeePortal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    public void createEmployee(EmployeeRequest employeeRequest){
        Employee employee = Employee.builder()
                .userName(employeeRequest.getUserName())
                .password(passwordEncoder.encode(employeeRequest.getPassword()))
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .phone(employeeRequest.getPhone())
                .role(employeeRequest.getRole())
                .build();
        employeeRepository.save(employee);
        log.info("New employee record created with ID: {}!", employee.getId());
    }

    public List<EmployeeResponse> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        log.info("Get All Employees!");
        return employees.stream().map(e -> EmployeeResponse.builder()
                .id(e.getId())
                .userName(e.getUserName())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .phone(e.getPhone())
                .role(e.getRole()).build()).toList();
    }

    public List<EmployeeResponse> getEmployeeById(Integer id){
        Optional<Employee> employee = employeeRepository.findById(id);
        log.info("Get Employee data by using ID {}", id);
        return employee.stream().map(e -> EmployeeResponse.builder()
                .id(e.getId())
                .userName(e.getUserName())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .phone(e.getPhone())
                .role(e.getRole()).build()).toList();
    }

    public List<EmployeeResponse> searchForEmployee(SearchEmployeeRequest searchEmployeeRequest){
        List<Employee> employeeList = employeeRepository.findByFirstNameOrLastNameOrEmail(
                searchEmployeeRequest.getFirstName(),
                searchEmployeeRequest.getLastName(),
                searchEmployeeRequest.getEmail()
        );
        log.info("Search for employee by using firstName/lastName/email");
        return employeeList.stream().map(e -> EmployeeResponse.builder()
                .id(e.getId())
                .userName(e.getUserName())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .phone(e.getPhone())
                .role(e.getRole()).build()).toList();
    }

    public void updateEmployee(EmployeeRequest employeeRequest){
        Optional<Employee> emp = employeeRepository.findById(employeeRequest.getId());
        if (emp.isPresent()){
            Employee employee = Employee.builder()
                    .id(employeeRequest.getId())
                    .userName(employeeRequest.getUserName() == null ? emp.get().getUserName() : employeeRequest.getUserName())
                    .firstName(employeeRequest.getFirstName() == null ? emp.get().getFirstName() : employeeRequest.getFirstName())
                    .password(employeeRequest.getPassword() == null ? emp.get().getPassword() : employeeRequest.getPassword())
                    .lastName(employeeRequest.getLastName() == null ? emp.get().getLastName() : employeeRequest.getLastName())
                    .email(employeeRequest.getEmail() == null ? emp.get().getEmail() : employeeRequest.getEmail())
                    .phone(employeeRequest.getPhone() == null ? emp.get().getPhone() : employeeRequest.getPhone())
                    .role(employeeRequest.getRole() == null ? emp.get().getRole() : employeeRequest.getRole())
                    .build();
            employeeRepository.save(employee);
        } else {
            Employee employee = Employee.builder()
                    .password(passwordEncoder.encode(employeeRequest.getPassword()))
                    .userName(employeeRequest.getUserName())
                    .firstName(employeeRequest.getFirstName())
                    .lastName(employeeRequest.getLastName())
                    .email(employeeRequest.getEmail())
                    .phone(employeeRequest.getPhone())
                    .role(employeeRequest.getRole())
                    .build();
            employeeRepository.save(employee);
        }
        log.info("Updated details for employee ID: {}!", employeeRequest.getId());
    }

    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
        log.info("Employee with Id: {} is deleted!", id);
    }

}
