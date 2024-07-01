plugins {
	kotlin("android")
	id("com.android.library")
	id("maven-publish")
}

group = "fr.beapp.utils"
version = "1.0.0"

android {
	namespace = "${project.group}.${project.name}"
	compileSdk = 34
	version = project.version.toString()

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
	testImplementation(libs.junit)
	implementation(libs.lifecycleRuntime)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}

afterEvaluate {
	publishing {
		publications {
			create<MavenPublication>(project.name) {
				from(components["release"])
				groupId = project.group.toString()
				artifactId = project.name
				version = project.version.toString()
				repositories {
					maven {
						url = uri("${rootProject.projectDir}/repo")
					}
				}
			}
		}

	}
}