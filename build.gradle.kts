plugins {
	kotlin("jvm")
	id("fabric-loom")
	`maven-publish`
	java
}

group = property("maven_group")!!
version = property("mod_version")!!

base {
	archivesName.set(property("archives_base_name") as String)
}

repositories {
	// Add repositories to retrieve artifacts from in here.
	maven { url = uri("https://maven.shedaniel.me/") }
	maven { url = uri("https://maven.terraformersmc.com/releases/") }
	maven(uri("https://maven.isxander.dev/releases"))
	maven("https://maven.terraformersmc.com/") { name = "Terraformers" }
	maven("https://maven.midnightdust.eu/releases")
}

dependencies {
	minecraft("com.mojang:minecraft:${property("minecraft_version")}")
	mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

	modImplementation("com.terraformersmc:modmenu:${property("modmenu_version")}")

//	val midnightlib = "eu.midnightdust:midnightlib:${property("midnightlib_version")}"
//	modImplementation(midnightlib)
//	include(midnightlib)

	// Just comment this.
	modImplementation("dev.isxander:yet-another-config-lib:${property("yacl_version")}+1.20.1-fabric")
//	modImplementation(files(".lib/cc/house-arrest-ankle-monitor.jar"))
//	runtimeOnly(files(".lib/cc/freeze-toggle-hit.jar"))
//	modImplementation(files(".lib/cc/freeze-toggle-hit.jar"))
}

tasks {
	processResources {
		inputs.property("version", project.version)
		filesMatching("fabric.mod.json") {
			expand(mapOf("version" to project.version))
		}
	}

	jar {
		from("LICENSE")
	}

	withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		compilerOptions {
			jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
		}
	}
}

java {
	withSourcesJar()
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			artifactId = project.property("archives_base_name") as String
			from(components["java"])
		}
	}

	repositories {
		// Add your repositories here
	}
}