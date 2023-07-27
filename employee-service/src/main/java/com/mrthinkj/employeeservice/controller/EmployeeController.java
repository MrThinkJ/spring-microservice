package com.mrthinkj.employeeservice.controller;

import com.mrthinkj.employeeservice.dto.APIResponseDto;
import com.mrthinkj.employeeservice.dto.EmployeeDto;
import com.mrthinkj.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
}
