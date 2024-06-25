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
		jcenter()
	}
}

rootProject.name = "fr.beapp.utils"
include(":app")
include(":androidXMLView")
include(":compose")
include(":kotlin")
include(":lifecycle")
include(":system")
include(":bom")
