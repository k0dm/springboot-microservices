package github.com.k0dm.departmentservice.repository

import github.com.k0dm.departmentservice.entity.Department
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository : JpaRepository<Department, Long> {

    fun findByDepartmentCode(code: String): Department
}