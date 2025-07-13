package com.hanbang.satto

import convention.androidExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * @author   JGeun
 * @created  2025/07/14
 */
class SattoFeaturePlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply("satto.android.library")
				apply("satto.android.compose")
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
		}
	}
}