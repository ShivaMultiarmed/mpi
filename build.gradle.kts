plugins {
    kotlin("jvm") version "2.0.0"
    application
}

group = "mikhail.shell.mpi"
version = "1.0-SNAPSHOT"

application {
    mainClass = "mikhail.shell.commis.voyager.TSPBruteForce"
}

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
    jvmToolchain(17)
}