package github.com.k0dm.departmentservice.mapper

import github.com.k0dm.departmentservice.dto.DepartmentDto
import github.com.k0dm.departmentservice.entity.Department
import org.mapstruct.Mapper

@Mapper
interface DepartmentMapper {

    fun toDepartmentDto(department: Department): DepartmentDto
    fun toDepartment(departmentDto: DepartmentDto): Department
}