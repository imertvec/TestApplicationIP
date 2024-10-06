plugins {
    alias(libs.plugins.ruvagavagus.android.application)
}

dependencies {
    implementation(projects.core.design)

    implementation(projects.feature.root)
    implementation(projects.feature.devicesList)
    implementation(projects.feature.messages)
}