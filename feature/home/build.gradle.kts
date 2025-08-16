plugins {
    alias(libs.plugins.satto.android.feature)
    alias(libs.plugins.satto.android.compose)
    alias(libs.plugins.satto.hilt.plugin)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = "com.hanbang.home"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)

    implementation(libs.kotlin.serialization.json)
}