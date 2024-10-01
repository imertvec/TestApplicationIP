import org.gradle.api.JavaVersion

object Config {
    private const val BASE_PACKAGE_NAME = "ru.vagavagus"
    const val APPLICATION_ID = "ru.vagavagus.testapplicationip"

    /*Sdk configuration*/
    const val MIN_SDK = 24
    const val TARGET_SDK = 34
    const val COMPILE_SDK = 34

    /*Version configuration*/
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    val JAVA_VERSION = JavaVersion.VERSION_17

    fun withBase(moduleName: String): String {
        return "$BASE_PACKAGE_NAME.$moduleName"
    }
}