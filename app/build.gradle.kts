plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.relojdevida"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.relojdevida"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Incluir la clave de API en BuildConfig
        buildConfigField("String", "apiKey", "\"${project.findProperty("apiKey") ?: ""}\"")
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

    buildFeatures {
        viewBinding = true
        buildConfig = true  // Habilitar el uso de BuildConfig customizado
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.lottie)
    // Dependencias necesarias para Retrofit y OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofitConverterGson)
    implementation(libs.okhttp)

    // Dependencia para Gson
    implementation(libs.gson)
    implementation("com.google.ai.client.generativeai:generativeai:0.7.0")
}