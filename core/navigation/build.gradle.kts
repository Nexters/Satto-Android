plugins {
	alias(libs.plugins.satto.android.feature)
	alias(libs.plugins.satto.android.compose)
	alias(libs.plugins.satto.hilt.plugin)
	alias(libs.plugins.kotlin.plugin.serialization)
}

android {
	namespace = "com.hanbang.navigation"
}

dependencies {
	implementation(projects.domain)

	implementation(libs.kotlin.serialization.json)
}