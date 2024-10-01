package plugins

import Config
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import utils.applyPlugin
import utils.libs

class AndroidLibraryComposePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.android.library)
            applyPlugin(libs.plugins.jetbrains.kotlin.android)
            applyPlugin(libs.plugins.compose.compiler)

            extensions.configure<LibraryExtension> {
                compileSdk = Config.COMPILE_SDK

                compileOptions {
                    sourceCompatibility = Config.JAVA_VERSION
                    targetCompatibility = Config.JAVA_VERSION
                }

                buildFeatures.compose = true
                defaultConfig.minSdk = Config.MIN_SDK

                dependencies {
                    "implementation"(libs.androidx.core.ktx)
                    "implementation"(libs.androidx.lifecycle.runtime.ktx)
                    "implementation"(platform(libs.androidx.compose.bom))

                    "implementation"(libs.androidx.ui)
                    "implementation"(libs.androidx.ui.graphics)
                    "implementation"(libs.androidx.ui.tooling.preview)
                    "implementation"(libs.androidx.material3)
                }
            }
        }
    }
}