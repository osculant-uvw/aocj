plugins {
    java
    application
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

// One task per day
tasks.register<JavaExec>("day01") {
    group = "aoc25"
    description = "Run Day 1"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("aoc25.day01.Day01")
}

tasks.register<JavaExec>("day02") {
    group = "aoc25"
    description = "Run Day 2"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("aoc25.day02.Day02")
}

tasks.register<JavaExec>("day03") {
    group = "aoc25"
    description = "Run Day 3"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("aoc25.day03.Day03")
}

tasks.register<JavaExec>("day04") {
    group = "aoc25"
    description = "Run Day 4"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("aoc25.day04.Day04")
}

tasks.register<JavaExec>("day05") {
    group = "aoc25"
    description = "Run Day 5"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("aoc25.day05.Day05")
}

tasks.register<JavaExec>("day06") {
    group = "aoc25"
    description = "Run Day 6"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("aoc25.day06.Day06")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
