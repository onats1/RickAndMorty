plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("kapt")
}

android {
    namespace = "com.onats.rickandmorty"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.onats.rickandmorty"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.onats.rickandmorty.HiltTestRunner"
    }

    packaging {
        resources {
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
    implementation(libs.squareup.retrofit)
    implementation(libs.timber)
    implementation(libs.kotlinx.serialization)
    implementation(libs.jakewarton.retrofit.converter)
    implementation(libs.androidx.pagination)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.dagger.hilt)
    implementation(libs.dagger.hilt.navigation.compose)
    implementation(libs.androidx.viewmodel.compose)
    implementation(libs.okhttp.logging.interceptor)
    kapt(libs.dagger.hilt.compiler)

    testImplementation(libs.androidx.navigation.testing.android)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.mockitocore)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.pagination.test)
    testImplementation(libs.google.truth)
    testImplementation(libs.kotlinx.coroutines.testing)
    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(libs.roboelectric)
    kaptAndroidTest(libs.hilt.android.compiler)
   // androidTestImplementation(libs.roboelectric)
    androidTestImplementation(libs.okhttp.mockwebserver)
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
   // testImplementation(kotlin("test"))
}