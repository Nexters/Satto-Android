package convention

import org.gradle.api.Project

/**
 *
 * @author   JGeun
 * @created  2025/07/14
 */
fun Project.setNamespace(name: String) {
	androidExtension.apply {
		namespace = "com.hanbang.$name"
	}
}