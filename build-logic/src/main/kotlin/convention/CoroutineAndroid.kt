package convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *
 * @author   JGeun
 * @created  2025/07/14
 */
internal fun Project.configureCoroutineAndroid() {
	val libs = extensions.libs
	configureCoroutineKotlin()
	dependencies {
		implementation(libs.findLibrary("coroutines-android").get())
	}
}

internal fun Project.configureCoroutineKotlin() {
	val libs = extensions.libs
	dependencies {
		implementation(libs.findLibrary("coroutines-core").get())
		testImplementation(libs.findLibrary("coroutines-test").get())
	}
}