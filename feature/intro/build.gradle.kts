plugins {
	alias(libs.plugins.satto.android.feature)
	alias(libs.plugins.satto.android.compose)
	alias(libs.plugins.satto.hilt.plugin)
}

android {
	namespace = "com.hanbang.intro"
}

dependencies {
	implementation(projects.core.designsystem)
	implementation(projects.core.navigation)
	implementation(projects.domain)

	implementation("androidx.core:core-splashscreen:1.0.0")
}