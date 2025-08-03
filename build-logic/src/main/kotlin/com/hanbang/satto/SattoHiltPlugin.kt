package com.hanbang.satto

import convention.implementation
import convention.kapt
import convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
class SattoHiltPlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with (target) {
			with(pluginManager) {
				apply("com.google.dagger.hilt.android")
				apply("org.jetbrains.kotlin.kapt")
			}

			val libs = extensions.libs

			dependencies {
				implementation(libs.findLibrary("hilt").get())
				kapt(libs.findLibrary("hilt-compiler").get())
			}
		}
	}
}