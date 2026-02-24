plugins {
	alias(libs.plugins.satto.android.feature)
	alias(libs.plugins.satto.android.compose)
	alias(libs.plugins.satto.hilt.plugin)
	alias(libs.plugins.kotlin.plugin.serialization)
}

android {
	namespace = "com.hanbang.main"
}

dependencies {
	implementation(projects.core.designsystem)
	implementation(projects.core.navigation)

	implementation(projects.feature.home)
	implementation(projects.feature.fortune)
	implementation(projects.feature.history)
	implementation(projects.feature.mypage)
	implementation(projects.feature.mapSearch)

	implementation(libs.kotlin.serialization.json)
}