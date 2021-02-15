import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(Plugins.versions) version Versions.versionsPlugin
}

allprojects {
    repositories {
        maven("https://nexus.inventale.com/repository/maven-public/") {
            name = "nexusInventale"
            credentials {
                username = (findProperty("inventale.nexus.user") ?: System.getenv("NEXUS_LOGIN")) as? String
                password = (findProperty("inventale.nexus.password") ?: System.getenv("NEXUS_PWD")) as? String
            }
        }
        mavenCentral()
    }
}

subprojects {


}

tasks {

    // LocalStack Integration Tests can't run in parallel
    // that's why we build sequential run
    val itTasks = getTasksByName("integrationTest", true)
        .filterIsInstance<IntegrationTestTask>()
        .filter { it.runSequentially }
        .sortedBy{ it.priority }
    var previous: Task? = null
    for (task in itTasks) {
        if (previous != null) {
            task.mustRunAfter(previous)
        }
        previous = task
    }
}
