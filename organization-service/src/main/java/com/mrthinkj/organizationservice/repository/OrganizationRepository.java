package com.mrthinkj.organizationservice.repository;

import com.mrthinkj.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByOrganizationCode(String code);
}
