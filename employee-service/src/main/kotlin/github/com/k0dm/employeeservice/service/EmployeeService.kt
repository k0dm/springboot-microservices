package github.com.k0dm.employeeservice.service

import github.com.k0dm.employeeservice.dto.APIResponseDto
import github.com.k0dm.employeeservice.dto.DepartmentDto
import github.com.k0dm.employeeservice.dto.EmployeeDto
import github.com.k0dm.employeeservice.entity.Employee
import github.com.k0dm.employeeservice.exception.EmailAlreadyExistsException
import github.com.k0dm.employeeservice.exception.ResourceNotFoundException
import github.com.k0dm.employeeservice.repository.EmployeeRepository
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

interface EmployeeService {

    fun createEmployee(employeeDto: EmployeeDto): EmployeeDto

    fun getEmployeeById(id: Long): APIResponseDto

    fun getDefaultDepartment(id: Long, e: Exception): APIResponseDto

    fun getEmployeeByEmail(email: String): EmployeeDto

    fun getAllEmployees(): List<EmployeeDto>

    @Service
    class Base(
        private val repository: EmployeeRepository,
        private val modelMapper: ModelMapper,
        //private val apiClient: APIClient
        private val webClient: WebClient
    ) : EmployeeService {

        private val LOGGER = LoggerFactory.getLogger(EmployeeService::class.java)

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

       // @CircuitBreaker(name = "\${spring.application.name}", fallbackMethod = "getDefaultDepartment")
        @Retry(name = "\${spring.application.name}", fallbackMethod = "getDefaultDepartment")
        override fun getEmployeeById(id: Long): APIResponseDto {

            LOGGER.info("inside getEmployeeById() method")

            val employee = repository.findById(id).orElseThrow {
                throw ResourceNotFoundException("Employee", "id", id)
            }
            val employeeDto = modelMapper.map(employee, EmployeeDto::class.java)
            // val departmentDto = apiClient.getDepartmentByCode(employee.departmentCode)
            val departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/byCode/" + employee.departmentCode)
                .retrieve()
                .bodyToMono(DepartmentDto::class.java)
                .block()!!

            return APIResponseDto(employeeDto, departmentDto)
        }

        override fun getDefaultDepartment(id: Long, e: Exception): APIResponseDto {
            LOGGER.info("inside getDefaultDepartment() method")

            val employee = repository.findById(id).orElseThrow {
                throw ResourceNotFoundException("Employee", "id", id)
            }
            val employeeDto = modelMapper.map(employee, EmployeeDto::class.java)

            val departmentDto = DepartmentDto(
                null,
                "R&D Department",
                "Research and Development Department",
                "RD001"
            )

            return APIResponseDto(employeeDto, departmentDto)
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