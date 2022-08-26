import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `maven-publish`
    kotlin("jvm") version "1.7.10"
}

group = "net.insprill"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.19.2-R0.1-SNAPSHOT")
}

tasks {

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }

}

publishing {
    publications {
        register("maven", MavenPublication::class) {
            from(components["java"])
            pom {
                name.set(project.name)
                description.set("A Spigot library for creating custom advancements")
                url.set("https://github.com/Insprill/advancement-api")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("insprill")
                        name.set("Pierce Thompson")
                        url.set("https://github.com/Insprill")
                    }
                }
                scm {
                    url.set("https://github.com/Insprill/advancement-api")
                    connection.set("scm:git:git://github.com/Insprill/advancement-api.git")
                    developerConnection.set("scm:git:git@github.com:Insprill/advancement-api.git")
                }
                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/Insprill/advancement-api/issues")
                }
            }
        }
    }
}
