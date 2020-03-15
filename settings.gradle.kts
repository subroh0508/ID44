include(
    ":android:app",
    ":android:base",
    ":android:auth",
    ":android:features:signin",
    ":android:features:timeline",
    ":bridges:shared",
    ":bridges:auth",
    ":bridges:signin",
    ":bridges:timeline",
    ":shared:util",
    ":shared:model:model-auth",
    ":shared:model:model-account",
    ":shared:model:model-status",
    ":shared:presentation:presentation-signin",
    ":domain:signin:usecase-signin",
    ":domain:timeline:usecase-timeline",
    ":actions:actions-signin",
    ":data:api",
    ":data:infra:infra-auth",
    ":data:infra:infra-account",
    ":data:infra:infra-status",
    ":libraries:reactnativesupport",
    ":react-native-vector-icons",
    ":react-native-gesture-handler",
    ":react-native-reanimated",
    ":react-native-fast-image"
)

project(":bridges:shared").name = "bridges-shared"
project(":bridges:auth").name = "bridges-auth"
project(":bridges:signin").name = "bridges-signin"
project(":bridges:timeline").name = "bridges-timeline"

project(":react-native-vector-icons").projectDir = file("$rootDir/node_modules/react-native-vector-icons/android")
project(":react-native-gesture-handler").projectDir = file("$rootDir/node_modules/react-native-gesture-handler/android")
project(":react-native-reanimated").projectDir = file("$rootDir/node_modules/react-native-reanimated/android")
project(":react-native-fast-image").projectDir = file("$rootDir/node_modules/react-native-fast-image/android")

enableFeaturePreview("GRADLE_METADATA")
