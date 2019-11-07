import org.gradle.api.Project
import org.gradle.api.initialization.Settings

val Settings.REACT_NATIVE_NODE_MODULE_PATH get() = "$rootDir/frontend/node_modules"

val Project.REACT_NATIVE_PATH get() = "$rootDir/frontend"
val Project.REACT_NATIVE_NODE_MODULE_PATH get() = "$rootDir/frontend/node_modules"
