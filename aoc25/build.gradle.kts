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

tasks.register<JavaExec>("day03") {
    group = "aoc25"
    description = "Run Day 3"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("aoc25.day03.Day03")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
