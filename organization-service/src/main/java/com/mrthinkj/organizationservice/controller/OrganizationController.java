package com.mrthinkj.organizationservice.controller;

import com.mrthinkj.organizationservice.dto.OrganizationDto;
import com.mrthinkj.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/organizations")
public class OrganizationController {
    OrganizationService organizationService;

    @GetMapping("/{organization-code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable(name = "organization-code") String code){
        return ResponseEntity.ok(organizationService.getOrganizationByCode(code));
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }
}
