plugins {
    alias(libs.plugins.ruvagavagus.android.library.compose)
    alias(libs.plugins.kotlinx.serialization)
}

android.namespace = Config.withBase("core.navigation")

dependencies {
    api(libs.androidx.navigation.compose)

    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlin.reflection)
}