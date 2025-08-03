plugins {
	alias(libs.plugins.satto.android.feature)
	alias(libs.plugins.satto.android.compose)
}

android {
	namespace = "com.hanbang.onboarding"
}

dependencies {
	implementation(projects.core.designsystem)
	implementation(projects.domain)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}