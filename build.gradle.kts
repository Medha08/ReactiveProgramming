import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "me.medhapandey"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.spring.io/release")
    }
    maven {
        url = uri("https://repository.jboss.org/maven2")
    }
}

dependencies {
    implementation("junit:junit:4.13.1")
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    implementation ("io.projectreactor:reactor-core:3.4.12")
    testImplementation("io.projectreactor:reactor-test:3.4.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}