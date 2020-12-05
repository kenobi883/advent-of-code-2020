plugins {
    java
    kotlin("jvm") version "1.4.10"
}

group = "com.marcdenning.adventofcode"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
