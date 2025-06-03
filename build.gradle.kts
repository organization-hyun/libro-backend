import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.libro"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// 기본 Spring + Kotlin
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-configuration-processor")

	// MySQL
	runtimeOnly("com.mysql:mysql-connector-j")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	// 테스트
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.add("-Xjsr305=strict")
	}
}

// JPA 엔티티를 위한 all-open 설정
allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

// 테스트는 JUnit5 사용
tasks.withType<Test> {
	useJUnitPlatform()
}

// Kotlin 컴파일 설정
tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions {
		jvmTarget = "17"
	}
}
