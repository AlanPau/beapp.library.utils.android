plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.kover)
}

android {
	namespace = "fr.beapp.utils.kotlin"
	compileSdk = 34
	version = "1.0.0"

	defaultConfig {
		minSdk = 24
		targetSdk = 34

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

koverReport {
	defaults {
		// adds the contents of the reports of `release` Android build variant to default reports
		mergeWith("release")
	}
	filters {
		excludes {

		}
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.kotlinx.datetime)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}