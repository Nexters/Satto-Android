plugins {
	alias(libs.plugins.satto.kotlin.library)
}

dependencies {
	implementation(projects.domain)

	implementation(libs.hilt.core)
}