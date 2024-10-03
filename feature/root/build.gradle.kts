plugins {
    alias(libs.plugins.ruvagavagus.android.library.compose)
}

android.namespace = Config.withBase("feature.root")

dependencies {
    implementation(projects.core.design)
    implementation(projects.core.navigation)
}