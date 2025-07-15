plugins {
	alias(libs.plugins.satto.android.library)
}

android {
	namespace = "com.hanbang.local"
}

dependencies {
	implementation(projects.data)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}