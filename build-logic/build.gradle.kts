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
		register("androidApplication") {
			id = "satto.android.application"
			implementationClass = "SattoAndroidApplicationPlugin"
		}
		register("androidLibrary") {
			id = "satto.android.library"
			implementationClass = "SattoAndroidLibrary"
		}
	}
}