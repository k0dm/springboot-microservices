package github.com.k0dm.departmentservice.controller

import github.com.k0dm.departmentservice.dto.DepartmentDto
import github.com.k0dm.departmentservice.service.DepartmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/departments")
class DepartmentController {

    @Autowired
    private lateinit var departmentService: DepartmentService

    @PostMapping
    fun saveDepartment(@RequestBody departmentDto: DepartmentDto) =
        ResponseEntity(departmentService.saveDepartment(departmentDto), HttpStatus.CREATED)

    @GetMapping("byId/{id}")
    fun getDepartmentById(@PathVariable("id") id: Long) = ResponseEntity.ok(departmentService.getDepartmentById(id))

    @GetMapping("byCode/{code}")
    fun getDepartmentByCode(@PathVariable("code") code: String) = ResponseEntity.ok(departmentService.getDepartmentByCode(code))

    @GetMapping
    fun getAllDepartments() = ResponseEntity.ok(departmentService.getAllDepartments())

}