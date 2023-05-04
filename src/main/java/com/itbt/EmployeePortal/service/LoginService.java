package com.itbt.EmployeePortal.service;

import com.itbt.EmployeePortal.dto.LoginRequest;
import com.itbt.EmployeePortal.model.Employee;
import com.itbt.EmployeePortal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private Boolean isValidUser(LoginRequest loginRequest){
        Optional<Employee> employee = employeeRepository.findByUserName(loginRequest.getUserName());
        return passwordEncoder.matches(loginRequest.getPassword(), employee.get().getPassword());
    }

    public String login(LoginRequest loginRequest){
        if(isValidUser(loginRequest)){
            return jwtService.generateToken(loginRequest.getUserName());
        } else {
            throw new UsernameNotFoundException("Login details are incorrect!");
        }
    }
}
