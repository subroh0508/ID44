include(
    ":android:app",
    ":android:commons",
    ":android:features:signin",
    ":android:features:timeline",
    ":desktop:actions:actions-signin",
    ":shared:util",
    ":shared:model:model-auth",
    ":shared:model:model-account",
    ":shared:model:model-status",
    ":shared:presentation:presentation-signin",
    ":domain:signin:usecase-signin",
    ":domain:timeline:usecase-timeline",
    ":data:api",
    ":data:infra:infra-auth",
    ":data:infra:infra-account",
    ":data:infra:infra-status",
    ":react-native-vector-icons",
    ":react-native-gesture-handler",
    ":react-native-reanimated",
    ":react-native-fast-image"
)

project(":react-native-vector-icons").projectDir = file("$rootDir/node_modules/react-native-vector-icons/android")
project(":react-native-gesture-handler").projectDir = file("$rootDir/node_modules/react-native-gesture-handler/android")
project(":react-native-reanimated").projectDir = file("$rootDir/node_modules/react-native-reanimated/android")
project(":react-native-fast-image").projectDir = file("$rootDir/node_modules/react-native-fast-image/android")

enableFeaturePreview("GRADLE_METADATA")
