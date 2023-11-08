package github.com.k0dm.departmentservice

import feign.Capability
import feign.micrometer.MicrometerCapability
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class DepartmentServiceApplication {

	@Bean
	fun capability(registry: MeterRegistry?):Capability  {
		return MicrometerCapability(registry)
	}
}

fun main(args: Array<String>) {
	runApplication<DepartmentServiceApplication>(*args)
}
