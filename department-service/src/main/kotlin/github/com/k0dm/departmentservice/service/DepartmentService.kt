package github.com.k0dm.departmentservice.service

import github.com.k0dm.departmentservice.dto.DepartmentDto
import github.com.k0dm.departmentservice.entity.Department
import github.com.k0dm.departmentservice.mapper.DepartmentMapper
import github.com.k0dm.departmentservice.repository.DepartmentRepository
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface DepartmentService {

    fun saveDepartment(departmentDto: DepartmentDto): DepartmentDto

    fun getDepartmentById(id: Long): DepartmentDto

    fun getDepartmentByCode(code: String): DepartmentDto

    fun getAllDepartments(): List<DepartmentDto>

    @Service
    class Base(private val departmentRepository: DepartmentRepository) : DepartmentService {

        private val mapper = Mappers.getMapper(DepartmentMapper::class.java)

        override fun saveDepartment(departmentDto: DepartmentDto): DepartmentDto {
            return mapper.toDepartmentDto(departmentRepository.save(mapper.toDepartment(departmentDto)))
        }

        override fun getDepartmentById(id: Long): DepartmentDto {
            return mapper.toDepartmentDto(departmentRepository.findById(id).get())
        }

        override fun getDepartmentByCode(code: String): DepartmentDto {
            return mapper.toDepartmentDto(departmentRepository.findByDepartmentCode(code))
        }

        override fun getAllDepartments(): List<DepartmentDto> {
            return departmentRepository.findAll().map { mapper.toDepartmentDto(it) }
        }
    }
}