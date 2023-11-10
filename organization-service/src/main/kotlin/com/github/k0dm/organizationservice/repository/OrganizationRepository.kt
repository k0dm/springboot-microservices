package com.github.k0dm.organizationservice.repository

import com.github.k0dm.organizationservice.entity.Organization
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationRepository: JpaRepository<Organization, Long> {

    fun findByOrganizationCode(organizationCode:String) : Organization
}