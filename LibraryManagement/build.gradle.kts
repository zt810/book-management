plugins {
    id("java")
    id("application")
}

group = "org.library"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("Main")
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main/java"))
        }
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
