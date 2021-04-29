import buildsrc.Dependencies
import buildsrc.setupLibraryModule

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

setupLibraryModule()

dependencies {
    testImplementation(Dependencies.junitJavaTestImpl)
    androidTestImplementation(Dependencies.junitAndroidTestImpl)
    androidTestImplementation(Dependencies.espressoAndroidTestImpl)
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.material)
    implementation(Dependencies.coil)
    implementation(Dependencies.volley)
    implementation(Dependencies.okhttp)
    implementation(project(path = ":base"))
}