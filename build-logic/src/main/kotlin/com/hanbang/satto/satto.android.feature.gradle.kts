import convention.androidExtension

plugins {
	id("satto.android.library")
	id("satto.android.compose")
}

androidExtension.apply {
	packaging {
		resources {
			excludes.add("META-INF/**")
		}
	}
	defaultConfig {
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
}

dependencies {

}