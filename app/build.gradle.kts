plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 31
    buildToolsVersion = "31.0.0"

    defaultConfig {
        applicationId = "ru.burtsev.push_keeper"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
    }
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    android.buildFeatures.viewBinding = true
    android.buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = Versions.androidxCompose
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")

    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("com.google.android.material:material:1.5.0-alpha02")

    //--------Room--------
    implementation("androidx.room:room-runtime:${Versions.roomVersion}")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:${Versions.roomVersion}")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:${Versions.roomVersion}")
    //--------Room--------

    //--------Koin--------
    implementation("io.insert-koin:koin-core:${Versions.koinVersion}")
    implementation("io.insert-koin:koin-android:${Versions.koinVersion}")
    //--------Koin--------

    //--------Compose--------
    implementation("androidx.compose.ui:ui:${Versions.androidxCompose}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.androidxCompose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.androidxCompose}")
    implementation("androidx.compose.material:material:${Versions.androidxCompose}")
    implementation("androidx.activity:activity-compose:${Versions.androidxCompose}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.androidxCompose}")
    implementation("androidx.navigation:navigation-compose:${Versions.androidxCompose}")
    implementation("androidx.navigation:navigation-runtime-ktx:${Versions.navigationCompose}")
    implementation("androidx.navigation:navigation-compose:${Versions.navigationCompose}")
    //--------Compose--------

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
