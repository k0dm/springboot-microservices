package github.com.k0dm.employeeservice.dto

import github.com.k0dm.employeeservice.entity.Employee


data class EmployeeDto(

    var id: Long?,
    var firstName: String?,
    var lastName: String?,
    var email: String?
) {
    constructor(employee: Employee) : this(employee.id, employee.firstName, employee.lastName, employee.email)
}