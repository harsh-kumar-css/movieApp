import com.android.build.gradle.internal.tasks.factory.registerTask

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.services)
    alias(libs.plugins.jetbrains.kotlin.serialization) // serialization for type safe navigation
    alias(libs.plugins.hilt)             // dagger
    kotlin("kapt")                      // annotation processing
    jacoco
}

jacoco {
    toolVersion = "0.8.12"
}

android {
    namespace = "com.example.movieapp"
    compileSdk = 34

    buildFeatures{
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String","API_KEY",project.findProperty("API_KEY")?.toString() ?: "")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
//        debug{
//            signingConfig = null
//        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")

    implementation(libs.androidx.navigation.compose)  // compose navigation
    implementation(libs.androidx.hilt.navigation.compose)     // use hilt in compose
    implementation(libs.kotlinx.serialization.json)   // serialization for type safe navigation
    implementation(libs.coil)                         // coil for image loading
    implementation(libs.viewModel.compose)            // viewmodel in compose
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.room.runtime)            // room db
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.dagger.hilt.android)           // dagger hilt
    kapt(libs.dagger.hilt.compiler)                    // hilt annotation processing
    implementation(libs.paging.runtime)                // paging
    implementation(libs.paging.compose)
    implementation(libs.room.paging)
    implementation(libs.lottie.compose)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.named("testDebugUnitTest")) // Run tests before generating the report

    reports {
        xml.required.set(true)  // Required for SonarCloud
        html.required.set(false)
        csv.required.set(false)
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*")
    val debugTree = fileTree("${buildDir}/intermediates/javac/debug") {
        exclude(fileFilter)
    }
    val kotlinDebugTree = fileTree("${buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    sourceDirectories.setFrom(files("src/main/java", "src/main/kotlin"))
    classDirectories.setFrom(files(debugTree, kotlinDebugTree))
    executionData.setFrom(fileTree(buildDir) {
        include("**/*.exec", "**/*.ec")
    })
}
