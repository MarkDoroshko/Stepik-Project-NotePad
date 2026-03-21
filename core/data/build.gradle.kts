plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)                 // Для кодогенерации Room и Hilt
    alias(libs.plugins.hilt.android)        // БЫЛ ПРОПУЩЕН — нужен для @Module/@InstallIn
}

android {
    namespace = "com.example.data"
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
    // Наш domain-модуль — для доступа к Note, ContentItem, NotesRepository
    implementation(project(":core:domain"))

    // Room — ORM для работы с SQLite
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Hilt — DI-фреймворк
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Android KTX — для toUri() и прочих extension'ов
    implementation(libs.androidx.core.ktx)

    // УДАЛЕНЫ: appcompat и material — они для View-based UI, в data-слое не нужны

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
