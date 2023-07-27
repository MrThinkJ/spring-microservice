package com.mrthinkj.organizationservice.service.impl;

import com.mrthinkj.organizationservice.dto.OrganizationDto;
import com.mrthinkj.organizationservice.entity.Organization;
import com.mrthinkj.organizationservice.mapper.OrganizationMapper;
import com.mrthinkj.organizationservice.repository.OrganizationRepository;
import com.mrthinkj.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToEntity(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Organization organization = organizationRepository.findByOrganizationCode(code);
        return OrganizationMapper.mapToDto(organization);
    }
}
