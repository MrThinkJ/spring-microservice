package com.mrthinkj.employeeservice.service;

import com.mrthinkj.employeeservice.dto.APIResponseDto;
import com.mrthinkj.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long id);
}
