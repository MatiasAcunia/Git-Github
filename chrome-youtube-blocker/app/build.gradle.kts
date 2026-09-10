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
        versionCode = 1
        versionName = "1.0"
    }
}
