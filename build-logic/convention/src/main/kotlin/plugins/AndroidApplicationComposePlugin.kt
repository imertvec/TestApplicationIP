package plugins

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import utils.apply
import plugins_config.configureApplicationPlugin
import utils.libs

class AndroidApplicationComposePlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(libs.plugins.android.application)
            apply(libs.plugins.jetbrains.kotlin.android)
            apply(libs.plugins.compose.compiler)

            val extension = extensions.getByType<ApplicationExtension>()
            extension.configureApplicationPlugin(target)
        }
    }

}

