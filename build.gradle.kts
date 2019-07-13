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
