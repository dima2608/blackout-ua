plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.devtools.ksp)
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.com.google.gms.google.services)
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.august.ua.blackout"
    compileSdk = 34

    bundle {
        language {
            // Specifies that the app bundle should not support
            // configuration APKs for language resources. These
            // resources are instead packaged with each base and
            // dynamic feature APK.
            enableSplit = false
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("keys/storekey.jks")
            keyAlias = "keyalias"
            keyPassword = "keypassword"
            storePassword = "storepassword"
        }
    }

    defaultConfig {
        applicationId = "com.august.ua.balackout"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            applicationIdSuffix = ".app"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                "\"someURL\""
            )
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
            applicationIdSuffix = ".dev"
            versionNameSuffix = ".dev"

            buildConfigField(
                "String",
                "BASE_URL",
                "\"someURL\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.datastore.core.android)
    implementation(libs.androidx.datastore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.material.icons.extended)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.core.splashscreen)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.runtime.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.work)
    ksp(libs.hilt.android.compiler)
    implementation(libs.work.runtime.ktx)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.accompanist.permissions)
    implementation(libs.glide)
    implementation(libs.compose.shadows.plus)
    implementation(libs.lottie.compose)
    implementation(libs.appcompat)
    implementation(libs.kotlinx.serialization.json)
}