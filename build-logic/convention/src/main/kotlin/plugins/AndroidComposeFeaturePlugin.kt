package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import utils.applyPlugin
import utils.libs

class AndroidComposeFeaturePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.ruvagavagus.android.library.compose)

            dependencies {
                /*Base core dependencies*/
                "implementation"(project(":core:design"))
                "implementation"(project(":core:android-domain"))
                "implementation"(project(":core:navigation"))

                /*Other dependencies*/
                "implementation"(platform(libs.koin.bom))
                "implementation"(libs.koin.compose.viewmodel)
                "implementation"(libs.koin.android)

                "implementation"(libs.orbit.viewmodel)
            }
        }
    }
}