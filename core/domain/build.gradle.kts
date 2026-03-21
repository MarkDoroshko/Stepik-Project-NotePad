plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    // Coroutines — только core, без android (чистый Kotlin-модуль, нет Android SDK)
    implementation(libs.kotlinx.coroutines.core)

    // javax.inject — чтобы use-case'ы могли использовать @Inject без зависимости от Dagger/Hilt
    implementation(libs.javax.inject)
}
