package com.github.k0dm.organizationservice.dto

import java.time.LocalDateTime

data class OrganizationDto(
    var id: Long? = null,
    var organizationName: String? = null,
    var organizationDescription: String? = null,
    var organizationCode: String? = null,
    var createdDate: LocalDateTime? = null
)