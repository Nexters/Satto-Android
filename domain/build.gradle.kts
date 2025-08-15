plugins {
	alias(libs.plugins.satto.kotlin.library)
}

dependencies {
	implementation(libs.hilt.core)
	implementation(libs.coroutines.core)
}