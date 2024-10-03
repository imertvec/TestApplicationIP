plugins {
    alias(libs.plugins.ruvagavagus.android.library.compose)
}

android.namespace = Config.withBase("feature.devicesList")

dependencies {
    implementation(projects.core.navigation)
}