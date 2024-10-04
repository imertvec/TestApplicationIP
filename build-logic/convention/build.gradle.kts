plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    compileOnly(libs.android.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "ru.vagavagus.android.application.compose"
            implementationClass = "plugins.AndroidApplicationComposePlugin"
        }
        register("androidLibraryCompose") {
            id = "ru.vagavagus.android.library.compose"
            implementationClass = "plugins.AndroidLibraryComposePlugin"
        }
        register("androidComposeFeature") {
            id = "ru.vagavagus.android.feature"
            implementationClass = "plugins.AndroidComposeFeaturePlugin"
        }
    }
}