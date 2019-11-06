import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

fun Project.androidExt(configure: BaseExtension.() -> Unit) =
    (this as ExtensionAware).extensions.configure("android", configure)