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
        maven(url = "$REACT_NATIVE_NODE_MODULE_PATH/react-native/android")
        maven(url = "$REACT_NATIVE_NODE_MODULE_PATH/jsc-android/dist")
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
