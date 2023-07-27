package com.mrthinkj.employeeservice.service;

import com.mrthinkj.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentApiClient {
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);
}