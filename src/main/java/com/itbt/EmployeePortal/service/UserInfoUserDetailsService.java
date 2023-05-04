package com.itbt.EmployeePortal.service;

import com.itbt.EmployeePortal.dto.EmployeeInfoUserDetails;
import com.itbt.EmployeePortal.model.Employee;
import com.itbt.EmployeePortal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserInfoUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUserName(username);
        return employee.map(EmployeeInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found " + username));
    }
}
