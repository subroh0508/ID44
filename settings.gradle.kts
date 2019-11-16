import groovy.lang.Closure

apply(from = "$rootDir/frontend/node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
val applyNativeModulesSettingsGradle: Closure<Unit> by extra

applyNativeModulesSettingsGradle(settings)

include(
    ":android:app",
    ":android:base",
    ":android:auth",
    ":android:components:core",
    ":android:components:authentication",
    ":android:components:timeline",
    ":android:features:authentication",
    ":android:features:timeline",
    ":libraries:api",
    ":libraries:auth:infra",
    ":libraries:auth:domain:usecase:requestappcredential",
    ":libraries:auth:domain:usecase:requestaccesstoken",
    ":libraries:timeline:infra",
    ":libraries:timeline:domain:entity",
    ":libraries:timeline:domain:valueobject",
    ":libraries:timeline:domain:usecase:subscribe",
    ":libraries:timeline:domain:usecase:unsubscribe",
    ":react-native-vector-icons",
    ":react-native-gesture-handler",
    ":react-native-reanimated"
)

project(":react-native-vector-icons").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-vector-icons/android")
project(":react-native-gesture-handler").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-gesture-handler/android")
project(":react-native-reanimated").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-reanimated/android")

pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenLocal()
        maven("https://kotlin.bintray.com/kotlinx")
    }
}
