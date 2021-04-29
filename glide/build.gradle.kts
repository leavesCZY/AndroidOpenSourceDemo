import buildsrc.Dependencies
import buildsrc.setupLibraryModule

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

kapt {
    arguments {
        arg("EASYROUTER_MODULE_NAME", "easyRouterDemo")
    }
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
    implementation(project(path = ":base"))
    implementation(Dependencies.glide)
    kapt(Dependencies.glideKapt)
    implementation(Dependencies.okhttp)
}