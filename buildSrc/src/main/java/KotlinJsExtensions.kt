import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

val Project.kotlinMPPExt
    get() = extensions.getByType(KotlinMultiplatformExtension::class)

private val Project.enableKotlinMPP
    get() = extensions.findByType(KotlinMultiplatformExtension::class) != null
private val Project.enableTargetJs
    get() = kotlinMPPExt.targets.findByName(KotlinPlatformType.js.name) != null

fun Project.buildOnlyJs() {
    tasks.create("buildOnlyJs") {
        group = "build"

        gradle.afterProject {
            if (this == rootProject || !enableKotlinMPP) {
                return@afterProject
            }

            if (enableTargetJs) {
                afterEvaluate {
                    val buildTask = tasks.findByName("build") ?: return@afterEvaluate

                    dependsOn(buildTask)
                }
            }
        }
    }
}
