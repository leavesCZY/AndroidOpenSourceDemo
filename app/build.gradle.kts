import buildsrc.setupAppModule

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

kapt {
    arguments {
        arg("EASYROUTER_MODULE_NAME", project.name)
    }
}

setupAppModule()

dependencies {
    testImplementation(buildsrc.Dependencies.junitJavaTestImpl)
    androidTestImplementation(buildsrc.Dependencies.junitAndroidTestImpl)
    androidTestImplementation(buildsrc.Dependencies.espressoAndroidTestImpl)
    implementation(buildsrc.Dependencies.kotlinStdlib)
    implementation(buildsrc.Dependencies.coreKtx)
    implementation(buildsrc.Dependencies.constraintLayout)
    implementation(buildsrc.Dependencies.material)
    implementation(project(path = ":easyrouter_api"))
    implementation(project(path = ":easyrouter_annotation"))
    kapt(project(path = ":easyrouter_processor"))
    implementation(project(path = ":easyrouter_demo"))
    implementation(project(path = ":easyeventbus_demo"))
    implementation(project(path = ":retrofit"))
    implementation(project(path = ":glide"))
    implementation(project(path = ":coil"))
    implementation(project(path = ":constraint_layout"))
    implementation(project(path = ":motion_layout"))
    implementation(project(path = ":motion_event"))
    implementation(project(path = ":customview"))
    implementation(project(path = ":launchmode"))
    implementation(project(path = ":base"))
}