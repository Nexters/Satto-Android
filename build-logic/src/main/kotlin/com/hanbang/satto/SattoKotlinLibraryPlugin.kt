package com.hanbang.satto

import convention.configurePureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * @author   JGeun
 * @created  2025/07/14
 */
class SattoKotlinLibraryPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			configurePureKotlin()
		}
	}
}