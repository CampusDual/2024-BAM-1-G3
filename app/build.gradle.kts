plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    /* If uncomment this line you have to provide your google-services.json file in the app folder root
    ex: app/google-services.json
    */
    alias(libs.plugins.google.gms.google.services)

    alias(libs.plugins.google.firebase.crashlytics)

    id("com.google.devtools.ksp") version "2.1.0-1.0.29"

    id("com.google.dagger.hilt.android") version "2.53"

    /// id ("kotlin-kapt") // Comenta esta línea si usas KSP

}

android {
    namespace = "com.campusdual_grupo3.bookandgo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.campusdual_grupo3.bookandgo"
        minSdk = 28
        //noinspection OldTargetApi
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true

    }
}

dependencies {

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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //MockK
    testImplementation(libs.mockk)

    //Glide
    implementation(libs.glide)
    ksp(libs.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Google Gson
    implementation(libs.gson)

    //Logging Interceptor
    implementation(libs.logging.interceptor)

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    // kapt (libs.androidx.room.compiler.v260)
    ksp(libs.androidx.room.compiler)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //Kotlin Livecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Navigation Component
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    // DataStore Preferences
    implementation (libs.androidx.datastore.preferences)

    // API Google Splash Screen
    implementation(libs.core.splashscreen)
}