plugins {
    kotlin("jvm") version "2.2.0"
}

group = "dev.vwdh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.register<JavaExec>("runCli") {
    group = "application"
    description = "Run CLI"
    mainClass.set("infrastructure.adapters.in.cli.CliKt")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}