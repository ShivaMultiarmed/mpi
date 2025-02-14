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
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.openjfx:javafx:17")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}