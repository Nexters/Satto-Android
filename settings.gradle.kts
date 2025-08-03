includeBuild("build-logic")

pluginManagement {
	repositories {
		google {
			content {
				includeGroupByRegex("com\\.android.*")
				includeGroupByRegex("com\\.google.*")
				includeGroupByRegex("androidx.*")
			}
		}
		mavenCentral()
		gradlePluginPortal()
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "Satto"
include(":app")
include(":data")
include(":domain")
include(":remote")
include(":local")
include(":core:designsystem")
include(":feature:home")
include(":feature:fortune")
include(":feature:history")
include(":feature:mypage")
include(":feature:onboarding")
include(":feature:main")
include(":core:navigation")
