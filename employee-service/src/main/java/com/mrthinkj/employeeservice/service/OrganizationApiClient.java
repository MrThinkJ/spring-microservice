package com.mrthinkj.employeeservice.service;

import com.mrthinkj.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationApiClient {
    @GetMapping("/api/organizations/{organization-code}")
    public OrganizationDto getOrganizationByCode(@PathVariable(name = "organization-code") String code);
}
