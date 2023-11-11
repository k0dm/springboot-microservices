package github.com.k0dm.employeeservice.controller

import github.com.k0dm.employeeservice.dto.EmployeeDto
import github.com.k0dm.employeeservice.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/employees")
class EmployeeController {

    @Autowired
    private lateinit var service: EmployeeService

    @PostMapping
    fun createEmployee(@RequestBody employeeDto: EmployeeDto) =
        ResponseEntity(service.createEmployee(employeeDto), HttpStatus.CREATED)

    @GetMapping("/byId/{id}")
    fun getEmployeeById(@PathVariable("id") id: Long) = ResponseEntity.ok(service.getEmployeeById(id))

    @GetMapping("/byEmail/{email}")
    fun getEmployeeByEmail(@PathVariable("email") email: String) = ResponseEntity.ok(service.getEmployeeByEmail(email))

    @GetMapping()
    fun getAllEmployees() = ResponseEntity.ok(service.getAllEmployees())
}