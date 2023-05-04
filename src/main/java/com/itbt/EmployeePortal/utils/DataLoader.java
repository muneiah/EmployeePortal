package com.itbt.EmployeePortal.utils;

import com.itbt.EmployeePortal.model.Employee;
import com.itbt.EmployeePortal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.save(Employee.builder()
                        .userName("rajesh")
                        .password(passwordEncoder.encode("rajesh@123"))
                        .firstName("Rajesh")
                        .lastName("E")
                        .email("rajesh.e@itbt.com")
                        .phone("9212341567")
                        .role("ADMIN")
                .build());

        employeeRepository.save(Employee.builder()
                .userName("ramesh")
                .password(passwordEncoder.encode("ramesh@123"))
                .firstName("Ramesh")
                .lastName("G")
                .email("ramesh.g@itbt.com")
                .phone("9212388967")
                .role("HR")
                .build());

        employeeRepository.save(Employee.builder()
                .userName("suresh")
                .password(passwordEncoder.encode("suresh@123"))
                .firstName("Suresh")
                .lastName("N")
                .email("suresh.n@itbt.com")
                .phone("9219001567")
                .role("EMPLOYEE")
                .build());

        employeeRepository.save(Employee.builder()
                .userName("natraj")
                .password(passwordEncoder.encode("natraj@123"))
                .firstName("Natraj")
                .lastName("N")
                .email("natraj.n@itbt.com")
                .phone("9212341787")
                .role("HR")
                .build());

        employeeRepository.save(Employee.builder()
                .userName("manish")
                .password(passwordEncoder.encode("manish@123"))
                .firstName("Manish")
                .lastName("H")
                .email("manish.h@itbt.com")
                .phone("9212341500")
                .role("EMPLOYEE")
                .build());
    }
}
