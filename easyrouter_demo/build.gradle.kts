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
    testImplementation(buildsrc.Dependencies.junitJavaTestImpl)
    androidTestImplementation(buildsrc.Dependencies.junitAndroidTestImpl)
    androidTestImplementation(buildsrc.Dependencies.espressoAndroidTestImpl)
    implementation(buildsrc.Dependencies.kotlinStdlib)
    implementation(buildsrc.Dependencies.coreKtx)
    implementation(buildsrc.Dependencies.constraintLayout)
    implementation(buildsrc.Dependencies.material)
    implementation(project(path = ":base"))
    implementation(project(path = ":easyrouter_annotation"))
    implementation(project(path = ":easyrouter_api"))
    kapt(project(path = ":easyrouter_processor"))
}