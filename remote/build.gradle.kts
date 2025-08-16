plugins {
	alias(libs.plugins.satto.android.library)
	alias(libs.plugins.satto.hilt.plugin)
	alias(libs.plugins.kotlin.plugin.serialization)
}

android {
	namespace = "com.hanbang.remote"

	buildTypes {
		getByName("debug") {
			buildConfigField("String", "BASE_URL", "\"https://www.satto.io.kr/\"")
		}
		getByName("release") {
			buildConfigField("String", "BASE_URL", "\"https://www.satto.io.kr/\"")
		}
	}
}

dependencies {
	implementation(projects.data)

	implementation(libs.retrofit)
	implementation(libs.okhttp.interceptor)
	implementation(libs.retrofit.converter.serialization)
	implementation(libs.kotlin.serialization.json)
}