plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
	namespace = "fr.beapp.utils.compose"
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

	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.kotlinCompiler.get()
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.material3WindowSizeClass)
	implementation(project(":system"))
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}