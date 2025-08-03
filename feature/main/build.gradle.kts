plugins {
	alias(libs.plugins.satto.android.feature)
	alias(libs.plugins.satto.android.compose)
	alias(libs.plugins.satto.hilt.plugin)
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
}