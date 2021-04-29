import buildsrc.Dependencies

plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Dependencies.javapoet)
    implementation(project(path = ":easyeventbus_api"))
}