

plugins {

}

subprojects {
    apply(plugin = "java-library")

    repositories {
        mavenCentral()
        flatDir {
            dirs("lib")
        }
        maven("https://jitpack.io")
        maven("https://repo.aikar.co/content/groups/aikar/")
    }
}