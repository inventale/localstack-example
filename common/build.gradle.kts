plugins {
    `java-library`
}

group = "com.inventale.platform"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(Libs.localstack)
    api(Libs.guava)
}


