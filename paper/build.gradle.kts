plugins {
    id("io.papermc.paperweight.userdev") version "1.7.1"
}

dependencies {
    paperDevBundle("1.20.4-R0.1-SNAPSHOT")
    compileOnly(":civmodcore-paper-3.0.0-dev-all")
}