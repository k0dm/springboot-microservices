package github.com.k0dm.employeeservice

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@EnableFeignClients
@SpringBootApplication
class EmployeeServiceApplication {

	@Bean
	fun modelMapper() = ModelMapper()
}

fun main(args: Array<String>) {
	runApplication<EmployeeServiceApplication>(*args)
}
