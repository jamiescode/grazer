plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.jamiescode.grazer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jamiescode.grazer"
        minSdk = 26
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":login"))
    implementation(project(":theme"))
    implementation(project(":users"))

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.composeDebug)
    androidTestImplementation(platform(libs.compose.bom))

    // Hilt
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.testing)

    // Timber
    implementation(libs.timber)

    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.uitest)
}