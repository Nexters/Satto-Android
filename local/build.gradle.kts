plugins {
	alias(libs.plugins.satto.android.library)
	alias(libs.plugins.satto.hilt.plugin)
}

android {
	namespace = "com.hanbang.local"
}

dependencies {
	implementation(projects.data)

	implementation(libs.datastore)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}