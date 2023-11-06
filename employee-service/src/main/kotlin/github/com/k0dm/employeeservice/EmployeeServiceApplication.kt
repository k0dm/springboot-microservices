package github.com.k0dm.employeeservice

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class EmployeeServiceApplication {

	@Bean
	fun webClient() = WebClient.builder().build()

	@Bean
	fun modelMapper() = ModelMapper()
}

fun main(args: Array<String>) {
	runApplication<EmployeeServiceApplication>(*args)
}
