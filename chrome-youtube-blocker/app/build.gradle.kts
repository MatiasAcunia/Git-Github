plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.naini.chromeyoutubeblocker"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.naini.chromeyoutubeblocker"
        minSdk = 26
        targetSdk = 35
        versionCode = 4
        versionName = "1.3"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
kotlin { jvmToolchain(17) }
