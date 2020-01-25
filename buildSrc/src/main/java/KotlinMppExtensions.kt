
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.kotlinMPPExt(configure: KotlinMultiplatformExtension.() -> Unit) =
    (this as ExtensionAware).extensions.configure("kotlin", configure)

fun Project.kotlinMPPShared() = kotlinMPPExt {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    js { nodejs() }
}
