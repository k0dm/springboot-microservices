package com.github.k0dm.organizationservice.service

import com.github.k0dm.organizationservice.dto.OrganizationDto
import com.github.k0dm.organizationservice.entity.Organization
import com.github.k0dm.organizationservice.repository.OrganizationRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

interface OrganizationService {

    fun saveOrganization(organizationDto: OrganizationDto): OrganizationDto

    fun getOrganizationByCode(organizationCode: String): OrganizationDto

    fun getAllOrganizations(): List<OrganizationDto>

    @Service

    class Base(private val repository: OrganizationRepository, private val mapper: ModelMapper) : OrganizationService {

        override fun saveOrganization(organizationDto: OrganizationDto): OrganizationDto {


            val organization = mapper.map(organizationDto, Organization::class.java)
            println("gggg $organization")

            val savedOrganization = repository.save(organization)

            return mapper.map(savedOrganization, OrganizationDto::class.java)
        }

        override fun getOrganizationByCode(organizationCode: String): OrganizationDto {
            val organization = repository.findByOrganizationCode(organizationCode)
            return mapper.map(organization, OrganizationDto::class.java)
        }

        override fun getAllOrganizations(): List<OrganizationDto> {
            val organizations = repository.findAll()
            return organizations.map { mapper.map(it, OrganizationDto::class.java) }
        }
    }
}