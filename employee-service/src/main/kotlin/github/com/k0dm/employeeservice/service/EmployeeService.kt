package github.com.k0dm.employeeservice.service

import github.com.k0dm.employeeservice.dto.EmployeeDto
import github.com.k0dm.employeeservice.entity.Employee
import github.com.k0dm.employeeservice.exception.EmailAlreadyExistsException
import github.com.k0dm.employeeservice.exception.ResourceNotFoundException
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

        override fun createEmployee(employeeDto: EmployeeDto): EmployeeDto {
            val email = employeeDto.email
            repository.findAll().forEach {
                if (it.email == email) throw EmailAlreadyExistsException(email)
            }
            return modelMapper.map(
                repository.save(modelMapper.map(employeeDto, Employee::class.java)),
                EmployeeDto::class.java
            )!!
        }


        override fun getEmployeeById(id: Long): EmployeeDto {

            val employee = repository.findById(id).orElseThrow {
                throw ResourceNotFoundException("Employee", "id", id)
            }

            return modelMapper.map(employee, EmployeeDto::class.java)!!
        }


        override fun getEmployeeByEmail(email: String): EmployeeDto {
            val employee = repository.findByEmail(email).orElseThrow {
                throw ResourceNotFoundException("Employee", "email", email)
            }
            return modelMapper.map(employee, EmployeeDto::class.java)!!
        }

        override fun getAllEmployees() = repository.findAll().map { modelMapper.map(it, EmployeeDto::class.java) }
    }

}