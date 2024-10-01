package plugins

import Config
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import utils.applyPlugin
import utils.libs

class AndroidApplicationComposePlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.android.application)
            applyPlugin(libs.plugins.jetbrains.kotlin.android)
            applyPlugin(libs.plugins.compose.compiler)

            val extension = extensions.getByType<ApplicationExtension>()

            with(extension) {
                buildFeatures.compose = true
                compileSdk = Config.COMPILE_SDK

                compileOptions {
                    sourceCompatibility = Config.JAVA_VERSION
                    targetCompatibility = Config.JAVA_VERSION
                }

                with(target) {
                    extensions.configure<ApplicationExtension> {
                        namespace = Config.APPLICATION_ID
                        packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

                        defaultConfig {
                            applicationId = Config.APPLICATION_ID
                            minSdk = Config.MIN_SDK
                            targetSdk = Config.TARGET_SDK
                            versionCode = Config.VERSION_CODE
                            versionName = Config.VERSION_NAME

                            vectorDrawables.useSupportLibrary = true
                        }
                    }

                    dependencies {
                        "implementation"(libs.androidx.core.ktx)
                        "implementation"(libs.androidx.lifecycle.runtime.ktx)
                        "implementation"(libs.androidx.activity.compose)
                    }
                }
            }
        }
    }

}

