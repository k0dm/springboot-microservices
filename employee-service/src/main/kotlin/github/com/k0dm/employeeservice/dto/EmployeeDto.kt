package github.com.k0dm.employeeservice.dto

data class EmployeeDto(
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var departmentCode: String? = null,
    var organizationCode: String? = null
)