package github.com.k0dm.employeeservice.service

import github.com.k0dm.employeeservice.dto.APIResponseDto
import github.com.k0dm.employeeservice.dto.DepartmentDto
import github.com.k0dm.employeeservice.dto.EmployeeDto
import github.com.k0dm.employeeservice.entity.Employee
import github.com.k0dm.employeeservice.exception.EmailAlreadyExistsException
import github.com.k0dm.employeeservice.exception.ResourceNotFoundException
import github.com.k0dm.employeeservice.repository.EmployeeRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient

interface EmployeeService {

    fun createEmployee(employeeDto: EmployeeDto): EmployeeDto

    fun getEmployeeById(id: Long): APIResponseDto

    fun getEmployeeByEmail(email: String): EmployeeDto

    fun getAllEmployees(): List<EmployeeDto>

    @Service
    class Base(
        private val repository: EmployeeRepository,
        private val modelMapper: ModelMapper,
        private val webClient: WebClient
    ) : EmployeeService {

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


        override fun getEmployeeById(id: Long): APIResponseDto {

            val employee = repository.findById(id).orElseThrow {
                throw ResourceNotFoundException("Employee", "id", id)
            }
            val employeeDto = modelMapper.map(employee, EmployeeDto::class.java)

            val departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/byCode/" + employee.departmentCode)
                .retrieve()
                .bodyToMono(DepartmentDto::class.java)
                .block()

            return APIResponseDto(employeeDto, departmentDto!!)
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