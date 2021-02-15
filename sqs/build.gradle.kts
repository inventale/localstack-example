plugins {
    `java-library`
}

group = "com.inventale.platform"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(project(":common"))

    testImplementation(Libs.junit)
    testImplementation(Libs.junitEngine)
    testImplementation(Libs.junitParams)
}

tasks.test {
    useJUnitPlatform()
    // to use properties from /config in tests
    workingDir = rootDir
}


val integrationTest = task<IntegrationTestTask>("integrationTest") {
    runSequentially = true
    // priority is used to order the execution of integration tests that use LocalStack (it shares common resources)
    priority = 1
    filter { includeTestsMatching("*IT") }
    reports {
        junitXml.isEnabled = true
    }

    useJUnitPlatform()
}

integrationTest.outputs.upToDateWhen { false }