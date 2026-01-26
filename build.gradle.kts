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
	mavenCentral()
	// Add repositories to retrieve artifacts from in here.
	maven { url = uri("https://maven.shedaniel.me/") }
	maven { url = uri("https://maven.terraformersmc.com/releases/") }
	maven(uri("https://maven.isxander.dev/releases"))
	maven("https://maven.terraformersmc.com/") { name = "Terraformers" }
	maven("https://maven.midnightdust.eu/releases")

	exclusiveContent {
		forRepository {
			maven { name = "Modrinth"; url = uri("https://api.modrinth.com/maven") }
		}
		filter { includeGroup("maven.modrinth") }
	}
	exclusiveContent {
		forRepository {
			maven { url = uri("https://cursemaven.com") }
		}
		filter { includeGroup("curse.maven") }
	}
	maven { name = "ModMaven"; url = uri("https://modmaven.dev") }

	// owo-lib
	maven { url = uri("https://maven.wispforest.io/releases/") }
}

dependencies {
	minecraft("com.mojang:minecraft:${property("minecraft_version")}")
	mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")
	modImplementation("com.terraformersmc:modmenu:${property("modmenu_version")}")

	// Optionals
	modRuntimeOnly("maven.modrinth:emi:1.1.22+1.20.1+fabric")
	modRuntimeOnly("TechReborn:TechReborn-1.20.1:5.8.9")
	modRuntimeOnly("RebornCore:RebornCore-1.20.1:5.8.9")

	// Added energy.
	// https://modmaven.dev/
	modImplementation("teamreborn:energy:3.0.0")
	modApi("teamreborn:energy:3.0.0")
	include("teamreborn:energy:3.0.0")

	modImplementation("dev.isxander:yet-another-config-lib:${property("yacl_version")}+1.20.1-fabric")


	// owo-lib
	modImplementation("io.wispforest:owo-lib:${property("owo_version")}")
	include("io.wispforest:owo-sentinel:${property("owo_version")}")
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