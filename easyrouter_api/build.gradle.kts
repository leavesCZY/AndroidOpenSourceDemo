import buildsrc.setupLibraryModule

plugins {
    id("com.android.library")
    id("kotlin-android")
}

setupLibraryModule()

dependencies {
    testImplementation(buildsrc.Dependencies.junitJavaTestImpl)
    androidTestImplementation(buildsrc.Dependencies.junitAndroidTestImpl)
    androidTestImplementation(buildsrc.Dependencies.espressoAndroidTestImpl)
    implementation(buildsrc.Dependencies.kotlinStdlib)
    implementation(project(path = ":easyrouter_annotation"))
    implementation(project(path = ":base"))
}