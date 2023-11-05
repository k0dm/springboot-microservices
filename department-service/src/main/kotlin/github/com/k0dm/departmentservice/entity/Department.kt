package github.com.k0dm.departmentservice.entity

import github.com.k0dm.departmentservice.dto.DepartmentDto
import jakarta.persistence.*

@Entity
@Table(name = "departments")
data class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    var departmentName: String = "",
    var departmentDescription: String = "",
    var departmentCode: String = "",
)