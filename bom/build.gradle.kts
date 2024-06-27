plugins {
	id("maven-publish")
	id("java-platform")
}

group = "fr.beapp.utils"
version = "0.0.2"

javaPlatform {
	allowDependencies()
}

dependencies {
	constraints {
		api(project(":androidXMLView"))
		api(project(":compose"))
		api(project(":kotlin"))
		api(project(":lifecycle"))
		api(project(":system"))
	}
}

publishing {
	publications {
		create<MavenPublication>("bom") {
			from(components["javaPlatform"])
			groupId = project.group.toString()
			artifactId = "bom"
			version = project.version.toString()
			repositories {
				// For local repository:
				maven {
					url = uri("${rootProject.projectDir}/repo")
				}
				// For remote repository:
				// maven {
				//     url = "http://my.repository.url"
				//     credentials {
				//         username = 'user'
				//         password = 'password'
				//     }
				// }
			}
		}
	}
}