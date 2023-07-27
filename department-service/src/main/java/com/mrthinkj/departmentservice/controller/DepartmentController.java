package com.mrthinkj.departmentservice.controller;

import com.mrthinkj.departmentservice.dto.DepartmentDto;
import com.mrthinkj.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode){
        return ResponseEntity.ok(departmentService.getDepartmentByCode(departmentCode));
    }
}
