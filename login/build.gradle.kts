plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.detekt)
    kotlin("kapt")
}

android {
    namespace = "com.jamiescode.grazer.login"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":theme"))

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.composeDebug)
    androidTestImplementation(platform(libs.compose.bom))

    // Hilt
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.testing)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Data store preferences
    implementation(libs.datastore.preferences)

    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.uitest)
}