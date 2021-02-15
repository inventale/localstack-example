object Versions {
    //Plugins
    const val versionsPlugin = "0.36.0"

    //Libs
    const val guava = "30.1-jre"
    const val gson = "2.8.6"
    const val localstack = "0.2.7"

    // Libs for testing
    const val junit = "5.7.0"
}

object Plugins {
    const val versions = "com.github.ben-manes.versions"
}

object Libs {
    const val guava = "com.google.guava:guava:${Versions.guava}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val localstack = "cloud.localstack:localstack-utils:${Versions.localstack}"

    // Test libraries
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val junitParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
}
