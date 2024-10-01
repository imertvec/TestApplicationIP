package plugins_config

import Config
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import utils.libs

internal fun CommonExtension<*, *, *, *, *, *>.configureApplicationPlugin(
    target: Project
) {
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
            "implementation"(platform(libs.androidx.compose.bom))
            "implementation"(libs.androidx.ui)
            "implementation"(libs.androidx.ui.graphics)
            "implementation"(libs.androidx.ui.tooling.preview)
            "implementation"(libs.androidx.material3)
        }
    }

}