plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)      // БЫЛ ПРОПУЩЕН — нужен для @Composable функций
}

android {
    namespace = "com.example.ui"
    compileSdk = 36                         // ИСПРАВЛЕН — был сломанный синтаксис compileSdk { version = release(36) }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    // Domain — Content.kt использует ContentItem для отображения содержимого заметки
    implementation(project(":core:domain"))

    // Coil — загрузка изображений в AsyncImage
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons)

    // Android KTX
    implementation(libs.androidx.core.ktx)

    // УДАЛЕНЫ: appcompat, material, activity-compose — не нужны в UI-библиотеке
    // activity-compose нужен только в app (для setContent) и feature-модулях (для ActivityResultContracts)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
