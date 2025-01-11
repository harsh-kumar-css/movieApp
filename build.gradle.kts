// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("org.sonarqube") version "4.3.0.3225"
}

//sonarqube {
//    properties {
//        property("sonar.projectKey", "harsh-movie-app_harsh-movie-app") // Replace with your actual project key
//        property("sonar.organization", "harsh-movie-app") // Replace with your organization key
//        property("sonar.host.url", "https://sonarcloud.io")
//
//        // Specify sources to analyze
//        property("sonar.sources", "src/main")
//    }
//}

//sonarqube {
//    properties {
//        property("sonar.projectKey", "harsh-kumar-css") // Replace with your actual project key
//        property("sonar.organization", "harsh-kumar-css") // Replace with your organization key
//
//        // Specify sources to analyze
//        property("sonar.host.url", "https://sonarcloud.io")
//        property("sonar.coverage.jacoco.xmlReportPaths", "${project(":app").buildDir}/reports/jacoco/jacocoTestReport/jacocoTestReport.xml")
//        property("sonar.gradle.skipCompile", "true") // Skip compile tasks
//
//        property("sonar.mainSources", "app/src/main/java")
//
//        // Define the test source folder (app/src/test/java and app/src/androidTest/java)
//        property("sonar.testSources", "app/src/test/java, app/src/androidTest/java")
//
//        // Exclude unnecessary files (e.g., activities, fragments, resources) from main sources
//        property("sonar.exclusions", "**/*Activity.kt, **/*Fragment.kt, app/src/main/res/**/*, app/src/main/java/com/example/movieapp/ui/theme/**/*, **/*.xml, **/build.gradle.kts")
//
//        property("sonar.test.exclusions", "**/*Activity.kt, **/*Fragment.kt, **/*.xml")
//
//    }
//}

subprojects {
    afterEvaluate {
        // Apply SonarQube properties for each subproject
        sonarqube {
            properties {
                // Project Key and Organization
                property("sonar.projectKey", "harsh-kumar-css")
                property("sonar.organization", "harsh-kumar-css")
                property("sonar.host.url", "https://sonarcloud.io")

                // Include main source files
                property("sonar.mainSources", "src/main/java, src/main/kotlin")
                property("sonar.testSources", "src/test/java, src/androidTest/java")

                // Exclude unnecessary files
                property("sonar.exclusions", "**/*Activity.kt, **/*Fragment.kt, **/res/**/*, **/ui/theme/**/*, **/*.xml, **/build.gradle.kts")
                property("sonar.test.exclusions", "**/*Activity.kt, **/*Fragment.kt, **/*.xml")

                // Define coverage report paths dynamically for all subprojects
                property("sonar.coverage.jacoco.xmlReportPaths", subprojects
                    .map { "${it.buildDir}/reports/jacoco/jacocoTestReport/jacocoTestReport.xml" }
                    .joinToString(","))
            }
        }
    }
}

// Define root project-specific configuration
sonarqube {
    properties {
        // Common properties for the whole project
        property("sonar.projectKey", "harsh-kumar-css")
        property("sonar.organization", "harsh-kumar-css")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "${project(":app").buildDir}/reports/jacoco/jacocoTestReport/jacocoTestReport.xml")
        property("sonar.gradle.skipCompile", "true")  // Skip compile tasks
    }
}



//
//harsh-movie-app_harsh-movie-app
//
//harsh-kumar-css