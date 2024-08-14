plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.aman.foodium"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aman.foodium"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"

        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures.viewBinding = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.car.ui.lib)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.common.jvm)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

//   //  Testing
//   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.1")
   implementation("com.squareup.okhttp3:mockwebserver:4.9.0")
   implementation("com.google.truth:truth:1.4.4")
//
//
//   //  Dependencies
//  // implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
//  // implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21-2")
//   implementation("com.android.tools.build:gradle:4.1.3")
//   implementation("com.google.dagger:hilt-android-gradle-plugin:2.45")
   implementation("com.google.android.material:material:1.2.0")
    implementation ("dev.shreyaspatil.MaterialDialog:MaterialDialog:2.2.3")
//    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
    implementation("io.coil-kt:coil:2.6.0")
//
//
    // Lifecycle
   implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
   implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
   implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
//
//
//    // Hilt
//    implementation(libs.hilt.android)
//    ksp(libs.hilt.compiler)
////   implementation("com.google.dagger:hilt-android-compiler:2.51.1")
////   implementation("androidx.hilt:hilt-compiler:1.2.0")
////   implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
////   implementation("com.google.dagger:hilt-android:2.51.1")
     // implementation("com.google.dagger:hilt-android-compiler:2.44")
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp("androidx.room:room-compiler:2.6.1")

//
   //  Moshi
   implementation("com.squareup.moshi:moshi-kotlin:1.11.0")
   implementation("com.squareup.moshi:moshi-kotlin-codegen:1.11.0")


   // Retrofit
   implementation("com.squareup.retrofit2:retrofit:2.9.0")
   implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
//
//
//   //  Coroutines
//   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
//   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
//
//
//   //  Android
//   implementation(libs.androidx.appcompat.v120)
//   //implementation("androidx.activity:activity-ktx:1.9.1")
//  // implementation("androidx.core:core-ktx:1.3.2")
   implementation("androidx.constraintlayout:constraintlayout:2.1.4")
   implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
//
//
//   //  Room
////   kapt("androidx.room:room-compiler:2.6.1")
////
////   implementation("androidx.room:room-ktx:2.6.1")
////   implementation("androidx.room:room-runtime:2.6.1")

    //Text Dimensions
    implementation ("com.intuit.sdp:sdp-android:1.1.1")
    implementation ("com.intuit.sdp:sdp-android:1.1.1")

    implementation ("androidx.multidex:multidex:2.0.1")
}