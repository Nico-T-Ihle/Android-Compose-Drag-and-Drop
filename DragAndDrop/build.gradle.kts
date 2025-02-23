plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    kotlin("plugin.compose") version "2.0.0"
}

android {
    namespace = "com.nico.draganddrop"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    testOptions {
        targetSdk = 35
    }

    lint {
        targetSdk = 35
    }

    buildFeatures {
        compose = true
    }

    composeOptions {

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx.v160)
    implementation(libs.androidx.appcompat.v131)
    implementation(libs.material.v140)
    implementation(libs.androidx.runtime.v110)
    implementation(libs.androidx.navigation.compose.v240alpha10)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v340)
    implementation(libs.material)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.ui.tooling.preview)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(platform(libs.androidx.compose.bom.v20240200))


}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.nico"
                artifactId = "draganddrop"
                version = "1.0.0"
            }
        }
        repositories {
            maven {
                url = uri(project.layout.buildDirectory.dir("repo").get().asFile.toURI())
            }
        }
    }
}
