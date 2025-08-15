plugins {
	alias(libs.plugins.satto.android.feature)
	alias(libs.plugins.satto.android.compose)
}

android {
	namespace = "com.hanbang.designsystem"
}

dependencies {
	implementation(projects.domain)

	implementation("androidx.core:core-splashscreen:1.0.0")

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}