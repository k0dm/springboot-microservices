package com.github.k0dm.organizationservice

import feign.Capability
import feign.micrometer.MicrometerCapability
import io.micrometer.core.instrument.MeterRegistry
import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class OrganizationServiceApplication{

	@Bean
	fun capability(registry: MeterRegistry?): Capability {
		return MicrometerCapability(registry)
	}

	@Bean
	fun modelMapper() = ModelMapper()
}

fun main(args: Array<String>) {
	runApplication<OrganizationServiceApplication>(*args)
}
