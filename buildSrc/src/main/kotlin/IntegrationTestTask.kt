import org.gradle.api.tasks.Input
import org.gradle.api.tasks.testing.Test

open class IntegrationTestTask : Test() {
    @get:Input
    var runSequentially = false
    @get:Input
    var priority = 0

    init {
        description = "Runs integration tests."
        group = "verification"
        testLogging.showStandardStreams = true
    }
}