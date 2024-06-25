// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.android.test) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.com.google.devtools.ksp) apply false
    //alias(libs.plugins.com.google.gms.google.services) apply false
}
buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
        classpath("com.android.tools.build:gradle:8.1.0")
        //classpath("com.google.gms:google-services:4.4.0")
        //classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
    }
}