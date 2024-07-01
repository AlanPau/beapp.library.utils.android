// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	alias(libs.plugins.androidApplication) apply false
	alias(libs.plugins.jetbrainsKotlinAndroid) apply false
	id("org.sonarqube") version "5.0.0.4638"
	id("org.jetbrains.kotlinx.kover") version "0.7.5"
}

sonarqube {
	properties {
		property("sonar.sourceEncoding", "UTF-8")
		property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/kover/report.xml")
		property("sonar.androidLint.reportPaths", "system/build/reports/lint-results-debug.xml,androidXMLView/build/reports/lint-results-debug.xml,compose/build/reports/lint-results-debug.xml,kotlin/build/reports/lint-results-debug.xml,lifecyle/build/reports/lint-results-debug.xml")
	}
}

/*
subprojects {
	apply(plugin = "org.jetbrains.kotlinx.kover")
}*/

extensions.configure<kotlinx.kover.gradle.plugin.dsl.KoverReportExtension> {
	defaults {
		group = "fr.beapp.utils"
		description = "Generate a merged HTML coverage report for all subprojects"

		//mergeWith("release")
	}
}


