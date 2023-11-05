package github.com.k0dm.employeeservice.repository

import github.com.k0dm.employeeservice.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long>{

    fun findByEmail(email: String): Employee
}