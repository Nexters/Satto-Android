plugins {
	alias(libs.plugins.satto.android.application)
	alias(libs.plugins.satto.android.compose)
	alias(libs.plugins.satto.hilt.plugin)
}

android {
	namespace = "com.hanbang.satto"
	compileSdk = 35

	defaultConfig {
		applicationId = "com.hanbang.satto"
		minSdk = 26
		targetSdk = 35
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

//	signingConfigs {
//		create("release") {
//			storeFile = file(" ")
//			storePassword = "your-store-password"
//			keyAlias = "your-key-alias"
//			keyPassword = "your-key-password"
//		}
//	}

	buildTypes {
		release {
//			signingConfig = signingConfigs.getByName("release")
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			isDebuggable = false
		}
	}
}

dependencies {
	implementation(projects.domain)
	implementation(projects.data)
	implementation(projects.local)
	implementation(projects.remote)
	implementation(projects.core.designsystem)
	implementation(projects.core.navigation)

	implementation(projects.feature.intro)
	implementation(projects.feature.onboarding)
	implementation(projects.feature.main)
	implementation(projects.feature.fortune)
	implementation(projects.feature.history)
	implementation(projects.feature.mypage)
	implementation(libs.androidx.lifecycle.process)
}