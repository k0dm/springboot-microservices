package github.com.k0dm.employeeservice

import feign.Capability
import feign.micrometer.MicrometerCapability
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.micrometer.core.instrument.MeterRegistry
import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@EnableFeignClients
@SpringBootApplication
class EmployeeServiceApplication {

	@Bean
	fun capability(registry: MeterRegistry?): Capability {
		return MicrometerCapability(registry)
	}

	@Bean
	fun webClient() = WebClient.builder().build()

	@Bean
	fun modelMapper() = ModelMapper()
}

fun main(args: Array<String>) {
	runApplication<EmployeeServiceApplication>(*args)
}
