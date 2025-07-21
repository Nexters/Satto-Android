package convention

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 *
 * @author   JGeun
 * @created  2025/07/14
 */
internal fun Project.configurePureKotlin() {
	with(pluginManager) {
		apply("java-library")
		apply("org.jetbrains.kotlin.jvm")
	}

	extensions.getByType<JavaPluginExtension>().apply {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}

	extensions.getByType<KotlinJvmProjectExtension>().compilerOptions {
		jvmTarget.set(JvmTarget.JVM_17)
	}

	tasks.withType<KotlinCompile>().configureEach {
		compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
	}
}