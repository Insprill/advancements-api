import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("io.papermc.paperweight.userdev") version "1.3.8"
}

dependencies {
    compileOnly(project(":core"))
    paperDevBundle("1.19.2-R0.1-SNAPSHOT")
}

tasks {

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

}
