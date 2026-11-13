plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

// Keep the generated per-app language list limited to translations owned by this app.
// Android resource qualifiers place the locale before qualifiers such as "night".
val standardLocaleQualifier = Regex("^([a-z]{2}(?:-r[A-Z]{2})?)(?:-|$)", RegexOption.IGNORE_CASE)
val bcp47LocaleQualifier = Regex(
    "^(b\\+[a-z]{2,3}(?:\\+[a-z]{4})?(?:\\+(?:[a-z]{2}|[0-9]{3}))?(?:\\+[a-z0-9]{2,8})*)(?:-|$)",
    RegexOption.IGNORE_CASE,
)
val appLocales = buildSet {
    add("en") // The unqualified values/ resources use English.
    file("src/main/res").listFiles()
        ?.filter { it.isDirectory && it.name.startsWith("values-") }
        ?.forEach { directory ->
            val qualifiers = directory.name.removePrefix("values-")
            val locale = bcp47LocaleQualifier.find(qualifiers)?.groupValues?.get(1)
                ?: standardLocaleQualifier.find(qualifiers)?.groupValues?.get(1)
            if (locale != null) add(locale)
        }
}.sorted()

android {
    namespace = "com.deprecabile.appsleeper"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.deprecabile.appsleeper"
        minSdk = 37
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    androidResources {
        generateLocaleConfig = true
        localeFilters += appLocales
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2026.09.00")
    implementation(composeBom)
    implementation("androidx.activity:activity-compose:1.13.0")
    implementation("androidx.compose.material3:material3")

    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:core:1.7.0")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.4.1")
}
