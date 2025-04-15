import org.gradle.api.file.DuplicatesStrategy.EXCLUDE

plugins {
    kotlin("jvm") version "2.0.0"
    application
    java
    id ("org.openjfx.javafxplugin") version "0.1.0"
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
    val javafxVersion = "17"

    implementation("org.openjfx:javafx-base:$javafxVersion")
    implementation("org.openjfx:javafx-controls:$javafxVersion")
    implementation("org.openjfx:javafx-fxml:$javafxVersion")
    implementation("org.openjfx:javafx-graphics:$javafxVersion")
}

javafx {
    version = "17.0.1"
    //modules = listOf("javafx.controls")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.create<Jar>("graph-app") {
    archiveFileName = "graph-app.jar"
    manifest {
        attributes(
            "Main-Class" to "mikhail.shell.mpi.common.GraphWindowKt"
        )
    }
    from(sourceSets.main.get().output) {
        include("mikhail/shell/mpi/common/**")
    }
    dependsOn(configurations.runtimeClasspath)
    duplicatesStrategy = EXCLUDE
    from(
        {
            configurations.runtimeClasspath.get().filter { it.exists() }.map { zipTree(it) }
        }
    )
}