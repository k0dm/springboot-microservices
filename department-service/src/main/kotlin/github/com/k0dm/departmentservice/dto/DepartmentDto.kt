package github.com.k0dm.departmentservice.dto

data class DepartmentDto(
    var id: Long? = null,
    var departmentName: String? = null,
    var departmentDescription: String? = null,
    var departmentCode: String? = null,
)