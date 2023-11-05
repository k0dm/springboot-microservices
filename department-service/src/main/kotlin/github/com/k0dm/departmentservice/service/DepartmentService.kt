package github.com.k0dm.departmentservice.service

import github.com.k0dm.departmentservice.dto.DepartmentDto
import github.com.k0dm.departmentservice.entity.Department
import github.com.k0dm.departmentservice.repository.DepartmentRepository
import org.springframework.stereotype.Service

interface DepartmentService {

    fun saveDepartment(departmentDto: DepartmentDto): DepartmentDto

    fun getDepartmentById(id: Long): DepartmentDto

    fun getDepartmentByCode(code: String): DepartmentDto

    fun getAllDepartments(): List<DepartmentDto>

    @Service
    class Base(private val departmentRepository: DepartmentRepository) : DepartmentService {

        override fun saveDepartment(departmentDto: DepartmentDto): DepartmentDto {
            return DepartmentDto(departmentRepository.save(Department(departmentDto)))
        }

        override fun getDepartmentById(id: Long): DepartmentDto {
            return DepartmentDto(departmentRepository.findById(id).get())
        }

        override fun getDepartmentByCode(code: String): DepartmentDto {
            return DepartmentDto(departmentRepository.findByDepartmentCode(code))
        }

        override fun getAllDepartments(): List<DepartmentDto> {
            return departmentRepository.findAll().map { DepartmentDto(it) }
        }
    }
}