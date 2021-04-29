package buildsrc

import org.gradle.api.Project

val Project.isAppModule: Boolean
    get() = name == "app"

val Project.isJavaModule: Boolean
    get() = name == "easyeventbus_api" || name == "easyeventbus_processor"
            || name == "easyrouter_processor" || name == "easyrouter_annotation"

val Project.minSdk: Int
    get() = intProperty("minSdk")

val Project.targetSdk: Int
    get() = intProperty("targetSdk")

val Project.compileSdk: Int
    get() = intProperty("compileSdk")

val Project.versionName: String
    get() = stringProperty("versionName")

val Project.versionCode: Int
    get() = intProperty("versionCode")

private fun Project.intProperty(name: String): Int {
    return (property(name) as String).toInt()
}

private fun Project.stringProperty(name: String): String {
    return property(name) as String
}