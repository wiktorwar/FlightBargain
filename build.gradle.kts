buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
    }
}

plugins {
    id(BuildPlugins.detekt) version (Ver.detekt)
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    apply {
        this.from("${rootProject.projectDir}/detekt.gradle")
    }
}

tasks.register("clean").configure {
    delete("build")
}
