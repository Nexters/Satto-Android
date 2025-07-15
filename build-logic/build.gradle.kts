plugins {
	`kotlin-dsl`
}

dependencies {
	implementation(libs.android.gradle.plugin)
	implementation(libs.kotlin.gradle.plugin)
	compileOnly(libs.ksp.gradle.plugin)
	compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
	plugins {
		register("sattoAndroidApplication") {
			id = "satto.android.application"
			implementationClass = "com.hanbang.satto.SattoAndroidApplicationPlugin"
		}
		register("sattoAndroidLibrary") {
			id = "satto.android.library"
			implementationClass = "com.hanbang.satto.SattoAndroidLibraryPlugin"
		}
		register("sattoComposePlugin") {
			id = "satto.android.compose"
			implementationClass = "com.hanbang.satto.SattoComposePlugin"
		}
		register("sattoFeaturePlugin") {
			id = "satto.android.feature"
			implementationClass = "com.hanbang.satto.SattoFeaturePlugin"
		}
		register("sattoKotlinLibrary") {
			id = "satto.kotlin.library"
			implementationClass = "com.hanbang.satto.SattoKotlinLibraryPlugin"
		}
	}
}