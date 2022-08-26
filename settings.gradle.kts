pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "advancements-api"

include(":core")
include(":nms:v1_19_R1")
