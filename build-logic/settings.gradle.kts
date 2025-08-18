import java.net.URI

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
		maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots/") }
	}
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}
}
rootProject.name = "build-logic"