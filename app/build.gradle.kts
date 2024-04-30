plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt") version "1.9.23"
}

android {
    namespace = "com.tictechtown.qrconnect"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tictechtown.qrconnect"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

}

dependencies {
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.6.5")

    val room_version = "2.6.1"
    implementation("androidx.room:room-common:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    val nav_version = "2.7.7"
    val composeBom = platform("androidx.compose:compose-bom:2024.04.00")
    implementation(composeBom)
    testImplementation(composeBom)
    androidTestImplementation(composeBom)

    // Choose one of the following:
    // Material Design 3
    implementation("androidx.compose.material3:material3")
    // Android Studio Preview support
    debugImplementation("androidx.compose.ui:ui-tooling")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.9.0")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")


    implementation("com.lightspark:compose-qr-code:1.0.1")
    implementation("androidx.navigation:navigation-compose:$nav_version")


}