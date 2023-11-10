package github.com.k0dm.employeeservice.dto

import java.time.LocalDateTime

data class OrganizationDto(
    val id: Long? = null,
    val organizationName: String? = null,
    val organizationDescription: String? = null,
    val organizationCode: String? = null,
    val createdDate: LocalDateTime? = null
)