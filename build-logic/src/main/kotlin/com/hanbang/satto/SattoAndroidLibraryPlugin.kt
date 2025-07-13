package com.hanbang.satto

import convention.configureComposeAndroid
import convention.configureCoroutineAndroid
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
			configureComposeAndroid()
			configureCoroutineAndroid()
		}
	}
}