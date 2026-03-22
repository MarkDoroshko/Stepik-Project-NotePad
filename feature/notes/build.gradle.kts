plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)      // Compose-компилятор для @Composable
    alias(libs.plugins.ksp)                 // ДОБАВЛЕН — для кодогенерации Hilt
    alias(libs.plugins.hilt.android)        // ДОБАВЛЕН — для @HiltViewModel
}

android {
    namespace = "com.example.notes"
    compileSdk = 36

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
    // Наши модули
    implementation(project(":core:domain"))  // Use-case'ы, Note, ContentItem
    implementation(project(":core:ui"))      // Тема, цвета, DateFormatter

    // Hilt — DI для ViewModel
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)         // ДОБАВЛЕН — без этого @HiltViewModel не генерирует код
    implementation(libs.androidx.hilt.navigation.compose)  // hiltViewModel()

    // Coil — для AsyncImage в карточках заметок
    implementation(libs.coil.compose)

    // Navigation — для возможности использовать навигационные API
    implementation(libs.androidx.navigation.compose)

    // Lifecycle — для viewModelScope, collectAsState
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons)

    // Android KTX
    implementation(libs.androidx.core.ktx)

    // УДАЛЕНЫ: appcompat, material, activity-compose — не нужны в этом модуле

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
