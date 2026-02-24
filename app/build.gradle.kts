plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.preet.kotlintemplate"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.preet.kotlintemplate"
        minSdk = 24
        targetSdk = 35
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
    //implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
    //debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)

    // MVVM
    implementation(libs.lifecycleViewModel)
    implementation(libs.lifecycleLiveData)

    // Coroutines
    implementation(libs.coroutinesAndroid)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.recyclerview)
    implementation(libs.kotlin.stdlib)
    implementation(libs.sdp)
    implementation(libs.lottie)

    implementation(libs.gson)
}