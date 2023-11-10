package com.github.k0dm.organizationservice.controller

import com.github.k0dm.organizationservice.dto.OrganizationDto
import com.github.k0dm.organizationservice.service.OrganizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/organizations")
class OrganizationController {

    @Autowired
    private lateinit var service: OrganizationService


    @PostMapping
    fun saveOrganization(@RequestBody organizationDto: OrganizationDto) =
        ResponseEntity(service.saveOrganization(organizationDto), HttpStatus.CREATED)

    @GetMapping("/{code}")
    fun getOrganizationByCode(@PathVariable("code") organizationCode: String) =
        ResponseEntity.ok(service.getOrganizationByCode(organizationCode))

    @GetMapping
    fun getAllOrganizations() = ResponseEntity.ok(service.getAllOrganizations())

}