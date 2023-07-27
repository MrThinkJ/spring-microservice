package com.mrthinkj.departmentservice.service.impl;

import com.mrthinkj.departmentservice.dto.DepartmentDto;
import com.mrthinkj.departmentservice.entity.Department;
import com.mrthinkj.departmentservice.repository.DepartmentRepository;
import com.mrthinkj.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepository departmentRepository;
    ModelMapper mapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = mapToEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = mapToDto(savedDepartment);
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        return mapToDto(department);
    }

    private Department mapToEntity(DepartmentDto departmentDto){
        return mapper.map(departmentDto, Department.class);
    }
    private DepartmentDto mapToDto(Department department){
        return mapper.map(department, DepartmentDto.class);
    }

}
