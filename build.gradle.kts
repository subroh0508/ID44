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

    // :react-native-vector-icons:verifyReleaseResourcesでAAPT2のエラーが出るのを防ぐ
    // https://github.com/facebook/react-native/issues/19239#issuecomment-425623992
    afterEvaluate {
        if (hasProperty("android")) {
            androidExt {
                compileSdkVersion(Package.Versions.compileSdk)
            }
        }
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
