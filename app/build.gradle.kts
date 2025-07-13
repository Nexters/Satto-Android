plugins {
	alias(libs.plugins.satto.android.application)
	alias(libs.plugins.satto.android.compose)
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

	signingConfigs {
		create("release") {

		}
	}

	buildTypes {
		release {
			signingConfig = signingConfigs.getByName("release")
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

}