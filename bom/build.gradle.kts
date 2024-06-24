plugins {
	id("io.github.gradlebom.generator-plugin").version("1.0.0.Final")
	id("signing")
}

group = "beapp.utils.bom"
version = "1.0.0"

publishing {
	publications {
		create<MavenPublication>("Bom") {
			artifactId = "utils-bom"

			pom {
				name.set("Beapp Utils BoM")
				description.set("Bill of Materials (BoM) for Beapp Utils libraries")
				// TODO url.set("")

				licenses {
					license {
						name.set("The Apache License, Version 2.0")
						url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
					}
				}

				issueManagement {
					system.set("Issues")
					// TODO url.set("")
				}

				scm {
					/*connection.set("")
					developerConnection.set("")
					url.set("")*/ // TODO if needed
				}
			}
		}
	}
}

signing {
	if (project.hasProperty("signing.gnupg.keyName")) {
		println("Signing lib...")
		useGpgCmd()
		sign(publishing.publications)
	}
}