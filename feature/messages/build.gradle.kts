plugins {
    alias(libs.plugins.ruvagavagus.android.library.compose)
}

android.namespace = Config.withBase("feature.messages")

dependencies {
    implementation(projects.core.navigation)
}
