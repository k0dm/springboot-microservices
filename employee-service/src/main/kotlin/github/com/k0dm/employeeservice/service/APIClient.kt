package github.com.k0dm.employeeservice.service

import github.com.k0dm.employeeservice.dto.DepartmentDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    url = "http://localhost:8080",
    value = "DEPARTMENT-SERVICE"
)
interface APIClient {

    @GetMapping("api/departments/byCode/{code}")
    fun getDepartmentByCode(@PathVariable("code") code: String): DepartmentDto
}