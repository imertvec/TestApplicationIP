plugins {
    alias(libs.plugins.ruvagavagus.android.library.compose)
}

android.namespace = Config.withBase("core.androidDomain")

dependencies {
    implementation(libs.orbit.viewmodel)
}