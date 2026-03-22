plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    // УДАЛЁН: kotlin.serialization — в app больше нет сериализации
}

android {
    namespace = "com.example.stepik_project_notepad"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.stepik_project_notepad"
        minSdk = 24
        targetSdk = 36
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    // === Наши модули ===
    // core:data — ОБЯЗАТЕЛЬНО в app, чтобы Hilt видел DI-модули (DatabaseModule, RepositoryModule)
    // Без этого Hilt не найдёт привязки и не сможет создать NotesRepository
    implementation(project(":core:data"))

    // core:domain — NavGraph использует Note для навигации
    implementation(project(":core:domain"))

    // core:ui — Theme (StepikProjectNotePadTheme) используется в MainActivity
    implementation(project(":core:ui"))

    // Feature-модули — каждый предоставляет свой экран для NavGraph
    implementation(project(":feature:notes"))
    implementation(project(":feature:creation"))
    implementation(project(":feature:editing"))

    // === Android / Compose ===
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)          // Для setContent {} в MainActivity
    implementation(libs.androidx.core.splashscreen)          // Splash Screen API

    // Compose — для NavGraph и MainActivity
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)

    // Navigation — NavHost и composable() живут в app
    implementation(libs.androidx.navigation.compose)

    // Hilt — @HiltAndroidApp в NotesApp, @AndroidEntryPoint в MainActivity
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // УДАЛЕНЫ из app (теперь в под-модулях):
    // - Room (в core:data)
    // - Coil (в core:ui и feature:notes)
    // - lifecycle-viewmodel-compose (в feature-модулях)
    // - lifecycle-runtime-ktx (в feature-модулях)
    // - hilt-navigation-compose (в feature-модулях)
    // - kotlinx-serialization-json (не используется)
    // - compose-ui-graphics, compose-ui-tooling-preview (не нужны в app напрямую)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
