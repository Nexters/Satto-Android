package com.hanbang.satto

import convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * @author   JGeun
 * @created  2025/07/14
 */
class SattoAndroidApplicationPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply("com.android.application")
			}

			configureKotlinAndroid()
		}
	}
}