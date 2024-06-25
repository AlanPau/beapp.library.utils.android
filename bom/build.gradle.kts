plugins {
	id("java-platform")
	id ("maven-publish")
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

			pom {
				name.set(project.name)
				description.set(project.provider(project::getDescription))
				url.set("${rootProject.projectDir}/repo") // TODO
				licenses {
					license {
						name.set("Apache License Version 2.0")
						url.set("https://www.apache.org/licenses/LICENSE-2.0")
					}
				}
			}
			repositories {
				// Define your repository here
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