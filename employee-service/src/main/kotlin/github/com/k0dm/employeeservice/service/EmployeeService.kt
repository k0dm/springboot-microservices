package github.com.k0dm.employeeservice.service

import github.com.k0dm.employeeservice.dto.EmployeeDto
import github.com.k0dm.employeeservice.entity.Employee
import github.com.k0dm.employeeservice.repository.EmployeeRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

interface EmployeeService {

    fun createEmployee(employeeDto: EmployeeDto): EmployeeDto

    fun getEmployeeById(id: Long): EmployeeDto

    fun getEmployeeByEmail(email: String): EmployeeDto

    fun getAllEmployees(): List<EmployeeDto>

    @Service
    class Base(private val repository: EmployeeRepository, private val modelMapper: ModelMapper) : EmployeeService {

        override fun createEmployee(employeeDto: EmployeeDto) =
            modelMapper.map(
                repository.save(modelMapper.map(employeeDto, Employee::class.java)),
                EmployeeDto::class.java
            )!!

        override fun getEmployeeById(id: Long) =
            modelMapper.map(repository.findById(id).get(), EmployeeDto::class.java)!!

        override fun getEmployeeByEmail(email: String) =
            modelMapper.map(repository.findByEmail(email), EmployeeDto::class.java)!!

        override fun getAllEmployees() = repository.findAll().map { modelMapper.map(it, EmployeeDto::class.java) }
    }

}