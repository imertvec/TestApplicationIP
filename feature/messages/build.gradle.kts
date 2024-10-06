plugins {
    alias(libs.plugins.ruvagavagus.android.feature)
}

android.namespace = Config.withBase("feature.messages")

dependencies {
    implementation(libs.kotlinx.datetime)
}