package github.com.k0dm.employeeservice.entity

import github.com.k0dm.employeeservice.dto.EmployeeDto
import jakarta.persistence.*

@Entity
@Table(name = "employees")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var firstName: String,
    var lastName: String,
    @Column(unique = true)
    var email: String
) {
    constructor(employeeDto: EmployeeDto) : this(employeeDto.id, employeeDto.firstName!!, employeeDto.lastName!!, employeeDto.email!!)
}