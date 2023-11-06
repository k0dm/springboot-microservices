package github.com.k0dm.employeeservice.dto

data class APIResponseDto(
    var employeeDto: EmployeeDto,
    var departmentDto: DepartmentDto
)