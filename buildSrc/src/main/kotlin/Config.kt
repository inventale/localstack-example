object Versions {
    //Plugins
    const val versionsPlugin = "0.36.0"

    //Libs
    const val guava = "30.1-jre"

    // Localstack + aws
    const val localstack = "0.2.7"
    const val awsJavaSdkV2 = "2.15.81" // we use aws sdk v2 (you can use v1 in your project as well)

    // Libs for testing
    const val junit = "5.7.0"
}

object Plugins {
    const val versions = "com.github.ben-manes.versions"
}

object Libs {
    const val guava = "com.google.guava:guava:${Versions.guava}"

    const val localstack = "cloud.localstack:localstack-utils:${Versions.localstack}"
    const val s3 = "software.amazon.awssdk:s3:${Versions.awsJavaSdkV2}"
    const val sqs = "software.amazon.awssdk:sqs:${Versions.awsJavaSdkV2}"

    // Test libraries
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val junitParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
}
