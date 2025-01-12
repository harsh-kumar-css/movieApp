// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("org.sonarqube") version "6.0.1.5171"
    jacoco
}

jacoco {
    toolVersion = "0.8.12"
}

sonar {
    properties {
        property("sonar.projectKey", "harsh-kumar-css") // Replace with your actual project key
        property("sonar.organization", "harsh-kumar-css") // Replace with your organization key

        // Specify sources to analyze
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "app/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml")
        property("sonar.gradle.skipCompile", "true") // Skip compile tasks
        property("sonar.branch.name", "main")
        property("sonar.mainSources", "app/src/main/java")

        // Define the test source folder (app/src/test/java and app/src/androidTest/java)
        property("sonar.testSources", "app/src/test/java, app/src/androidTest/java")

        // Exclude unnecessary files (e.g., activities, fragments, resources) from main sources
        property("sonar.exclusions", "**/*Activity.kt, **/*Fragment.kt, app/src/main/res/**/*, app/src/main/java/com/example/movieapp/ui/theme/**/*, **/*.xml, **/build.gradle.kts")

        property("sonar.test.exclusions", "**/*Activity.kt, **/*Fragment.kt, **/*.xml")
    }
}