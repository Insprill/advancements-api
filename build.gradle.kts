plugins {
    `maven-publish`
    kotlin("jvm") version "1.7.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

allprojects {
    group = "net.insprill"
    version = "0.1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":nms:v1_19_R1", "default"))
}

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveClassifier.set("")
    }

    build {
        dependsOn(shadowJar)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            project.shadow.component(this)
            pom {
                name.set(project.name)
                description.set("A Spigot library for creating custom advancements")
                url.set("https://github.com/Insprill/advancements-api")
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
                    url.set("https://github.com/Insprill/advancements-api")
                    connection.set("scm:git:git://github.com/Insprill/advancements-api.git")
                    developerConnection.set("scm:git:git@github.com:Insprill/advancements-api.git")
                }
                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/Insprill/advancements-api/issues")
                }
            }
        }
    }
}
