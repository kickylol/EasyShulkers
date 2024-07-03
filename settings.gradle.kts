pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
        flatDir {
            dirs("lib")
        }
    }
}

rootProject.name = "easyshulkers"

include(":paper")
project(":paper").name = rootProject.name + "-paper"
