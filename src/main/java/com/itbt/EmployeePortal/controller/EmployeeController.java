package com.itbt.EmployeePortal.controller;

import com.itbt.EmployeePortal.dto.EmployeeRequest;
import com.itbt.EmployeePortal.dto.EmployeeResponse;
import com.itbt.EmployeePortal.dto.SearchEmployeeRequest;
import com.itbt.EmployeePortal.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'HR')")
    public String addEmployee(@RequestBody EmployeeRequest employeeRequest){
        employeeService.createEmployee(employeeRequest);
        return "Employee Created!";
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'HR')")
    public String updateEmployee(@RequestBody EmployeeRequest employeeRequest){
        employeeService.updateEmployee(employeeRequest);
        return "Employee Updated!";
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteEmployee(@RequestParam(name = "id") Integer id) {
        employeeService.deleteEmployee(id);
        return "Employee Deleted!";
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<EmployeeResponse> getAllEmployee(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'HR', 'EMPLOYEE')")
    public List<EmployeeResponse> getEmployeeById(@RequestParam(name = "id") Integer id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'HR', 'EMPLOYEE')")
    public List<EmployeeResponse> searchEmployee(@RequestParam Map<String, String> requestParams){
        SearchEmployeeRequest searchEmployeeRequest = SearchEmployeeRequest.builder()
                .email(requestParams.get("email"))
                .firstName(requestParams.get("firstName"))
                .lastName(requestParams.get("lastName"))
                .build();
        return employeeService.searchForEmployee(searchEmployeeRequest);
    }


}
