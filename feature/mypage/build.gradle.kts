plugins {
    alias(libs.plugins.satto.android.feature)
    alias(libs.plugins.satto.android.compose)
    alias(libs.plugins.satto.hilt.plugin)
}

android {
    namespace = "com.hanbang.mypage"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
}