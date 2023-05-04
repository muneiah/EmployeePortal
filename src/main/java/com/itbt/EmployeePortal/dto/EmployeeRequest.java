package com.itbt.EmployeePortal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest {

    private Integer id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String role; // ADMIN, EMPLOYEE, HR
    private String email;
    private String phone;
}
