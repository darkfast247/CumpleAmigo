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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

    }

    kotlinOptions {
        jvmTarget = "17"
    }
    packagingOptions {
        // Excluir archivos duplicados
        resources.excludes += "META-INF/native-image/org.mongodb/bson/native-image.properties"
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

    implementation(libs.gson)
    implementation("com.google.ai.client.generativeai:generativeai:0.7.0")
    implementation ("io.realm:realm-android-library:10.10.0") // Última versión de MongoDB Realm

    implementation ("com.android.tools:desugar_jdk_libs:1.1.5")
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:1.1.5")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.10.1")
    implementation("org.slf4j:slf4j-simple:1.7.9")

    implementation ("com.google.code.gson:gson:2.8.9")









}