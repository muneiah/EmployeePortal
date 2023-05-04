package com.itbt.EmployeePortal.controller;

import com.itbt.EmployeePortal.dto.LoginRequest;
import com.itbt.EmployeePortal.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }
}

