plugins {
    id("java")
}

group = "com.edu.uce"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Source: https://mvnrepository.com/artifact/com.h2database/h2
    runtimeOnly("com.h2database:h2:2.5.252")
    // Source: https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
    implementation("org.hibernate.orm:hibernate-core:7.4.13.Final")
    // Source: https://mvnrepository.com/artifact/tools.jackson.core/jackson-databind
    implementation("tools.jackson.core:jackson-databind:3.2.4")
    // Source: https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.48")
    annotationProcessor("org.projectlombok:lombok:1.18.48")
}

tasks.test {
    useJUnitPlatform()
}