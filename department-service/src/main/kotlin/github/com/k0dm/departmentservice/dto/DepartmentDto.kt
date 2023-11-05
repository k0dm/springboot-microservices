package github.com.k0dm.departmentservice.dto

import github.com.k0dm.departmentservice.entity.Department

data class DepartmentDto(
    var id: Long?,
    var departmentName: String?,
    var departmentDescription: String?,
    var departmentCode: String?,
) {
    constructor(department: Department) : this(
        department.id,
        department.departmentName,
        department.departmentDescription,
        department.departmentCode
    )
}