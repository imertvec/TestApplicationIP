package utils

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.plugins.PluginAware
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.the
import org.gradle.plugin.use.PluginDependency

val Project.libs get() = the<LibrariesForLibs>()

fun PluginAware.apply(plugin: Provider<PluginDependency>) {
    apply(plugin = plugin.get().pluginId)
}