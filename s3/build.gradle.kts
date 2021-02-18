plugins {
    `java-library`
}

group = "com.inventale.platform"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(project(":common"))

    implementation(Libs.s3)

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
    priority = 2
    reports {
        junitXml.isEnabled = true
    }

    useJUnitPlatform{
        includeTags("IT")
    }
}

integrationTest.outputs.upToDateWhen { false }