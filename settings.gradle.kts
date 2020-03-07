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
    ":data:api",
    ":data:infra:infra-auth",
    ":libraries:reactnativesupport",
    ":libraries:account:infra",
    ":libraries:account:domain:usecase:fetchownaccount",
    ":libraries:account:domain:usecase:fetchownaccounts",
    ":libraries:auth:domain:usecase:requestappcredential",
    ":libraries:auth:domain:usecase:requestaccesstoken",
    ":libraries:auth:domain:usecase:switchaccesstoken",
    ":libraries:timeline:infra",
    ":libraries:timeline:domain:usecase:subscribe",
    ":libraries:timeline:domain:usecase:unsubscribe",
    ":libraries:timeline:domain:usecase:fetchstatuses",
    ":libraries:timeline:domain:usecase:submitstatus",
    ":libraries:timeline:domain:usecase:togglereblog",
    ":libraries:timeline:domain:usecase:togglefavourite",
    ":react-native-vector-icons",
    ":react-native-gesture-handler",
    ":react-native-reanimated",
    ":react-native-fast-image"
)

project(":libraries:account:infra").name = "account-infra"
project(":libraries:timeline:infra").name = "timeline-infra"

project(":bridges:shared").name = "bridges-shared"
project(":bridges:auth").name = "bridges-auth"
project(":bridges:signin").name = "bridges-signin"
project(":bridges:timeline").name = "bridges-timeline"

project(":react-native-vector-icons").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-vector-icons/android")
project(":react-native-gesture-handler").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-gesture-handler/android")
project(":react-native-reanimated").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-reanimated/android")
project(":react-native-fast-image").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-fast-image/android")

enableFeaturePreview("GRADLE_METADATA")
