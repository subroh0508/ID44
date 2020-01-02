include(
    ":android:app",
    ":android:base",
    ":android:auth",
    ":android:components:core",
    ":android:features:signin",
    ":android:features:timeline",
    ":bridges:shared",
    ":bridges:auth",
    ":bridges:signin",
    ":bridges:timeline",
    ":libraries:shared",
    ":libraries:reactnativesupport",
    ":libraries:api",
    ":libraries:account:infra",
    ":libraries:account:domain:entity",
    ":libraries:account:domain:usecase:fetchownaccount",
    ":libraries:account:domain:usecase:fetchownaccounts",
    ":libraries:auth:infra",
    ":libraries:auth:domain:valueobject",
    ":libraries:auth:domain:usecase:requestappcredential",
    ":libraries:auth:domain:usecase:requestaccesstoken",
    ":libraries:auth:domain:usecase:switchaccesstoken",
    ":libraries:timeline:infra",
    ":libraries:timeline:domain:entity",
    ":libraries:timeline:domain:valueobject",
    ":libraries:timeline:domain:usecase:subscribe",
    ":libraries:timeline:domain:usecase:unsubscribe",
    ":libraries:timeline:domain:usecase:fetchstatuses",
    ":react-native-vector-icons",
    ":react-native-gesture-handler",
    ":react-native-reanimated"
)

project(":libraries:auth:domain:valueobject").name = "auth-valueobject"
project(":libraries:account:domain:entity").name = "account-entity"
project(":libraries:timeline:domain:entity").name = "timeline-entity"
project(":libraries:timeline:domain:valueobject").name = "timeline-valueobject"

project(":react-native-vector-icons").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-vector-icons/android")
project(":react-native-gesture-handler").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-gesture-handler/android")
project(":react-native-reanimated").projectDir = file("$REACT_NATIVE_NODE_MODULE_PATH/react-native-reanimated/android")
