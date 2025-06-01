package com.libro.librobackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class LibroBackendApplication

fun main(args: Array<String>) {
	runApplication<LibroBackendApplication>(*args)
}
