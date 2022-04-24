import org.jetbrains.kotlin.gradle.tasks.KotlinCompIsland

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "me.ozaga"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompIsland>() {
    kotlinOptions.jvmTarget = "1.8"
}