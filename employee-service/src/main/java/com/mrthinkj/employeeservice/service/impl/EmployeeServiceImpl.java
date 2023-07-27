package com.mrthinkj.employeeservice.service.impl;

import com.mrthinkj.employeeservice.dto.APIResponseDto;
import com.mrthinkj.employeeservice.dto.DepartmentDto;
import com.mrthinkj.employeeservice.dto.EmployeeDto;
import com.mrthinkj.employeeservice.dto.OrganizationDto;
import com.mrthinkj.employeeservice.entity.Employee;
import com.mrthinkj.employeeservice.repository.EmployeeRepository;
import com.mrthinkj.employeeservice.service.DepartmentApiClient;
import com.mrthinkj.employeeservice.service.EmployeeService;
import com.mrthinkj.employeeservice.service.OrganizationApiClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    ModelMapper mapper;
    WebClient webClient;
    DepartmentApiClient departmentApiClient;
    OrganizationApiClient organizationApiClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToDto(savedEmployee);
    }

    @Override
//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long id) {
        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(id).get();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(mapToDto(employee));

        // WebClient
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

//        OrganizationDto organizationDto = webClient.get()
//                .uri("http://localhost:8080/api/organizations/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(OrganizationDto.class)
//                .block();

        // OpenFeign
//        DepartmentDto departmentDto = departmentApiClient.getDepartmentByCode(employee.getDepartmentCode());
//        OrganizationDto organizationDto = organizationApiClient.getOrganizationByCode(employee.getOrganizationCode());

        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }

    private Employee mapToEntity(EmployeeDto employeeDto){
        return mapper.map(employeeDto, Employee.class);
    }

    private EmployeeDto mapToDto(Employee employee){
        return mapper.map(employee, EmployeeDto.class);
    }

    public APIResponseDto getDefaultDepartment(Long id, Throwable throwable){
        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(mapToDto(employee));
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
