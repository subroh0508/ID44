buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Libraries.GradlePlugin.kotlin)
        classpath(Libraries.GradlePlugin.android)
        classpath(Libraries.GradlePlugin.kotlinSerialization)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "$rootDir/frontend/node_modules/react-native/android")
        maven(url = "$rootDir/frontend/node_modules/jsc-android/dist")
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
