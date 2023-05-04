package com.itbt.EmployeePortal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {

    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String role; // ADMIN, EMPLOYEE, HR
    private String email;
    private String phone;
}
