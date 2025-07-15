package com.hanbang.satto

import convention.configureCoroutineAndroid
import convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * @author   JGeun
 * @created  2025/07/14
 */
class SattoAndroidLibraryPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply("com.android.library")

			configureKotlinAndroid()
			configureCoroutineAndroid()
		}
	}
}