package github.com.k0dm.employeeservice.service

import github.com.k0dm.employeeservice.dto.EmployeeDto
import github.com.k0dm.employeeservice.entity.Employee
import github.com.k0dm.employeeservice.repository.EmployeeRepository
import org.springframework.stereotype.Service

interface EmployeeService {

    fun createEmployee(employeeDto: EmployeeDto): EmployeeDto

    fun getEmployeeById(id: Long): EmployeeDto

    fun getEmployeeByEmail(email: String): EmployeeDto

    fun getAllEmployees(): List<EmployeeDto>

    @Service
    class Base(private val repository: EmployeeRepository) : EmployeeService {

        override fun createEmployee(employeeDto: EmployeeDto) = EmployeeDto(repository.save(Employee(employeeDto)))

        override fun getEmployeeById(id: Long) = EmployeeDto(repository.findById(id).get())

        override fun getEmployeeByEmail(email: String) = EmployeeDto(repository.findByEmail(email))

        override fun getAllEmployees() = repository.findAll().map { EmployeeDto(it) }
    }

}