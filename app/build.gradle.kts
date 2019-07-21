plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}
androidExtensions { isExperimental = true }

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "com.madwiktor.flightbargain"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }


}

dependencies {
    implementation(project(":core"))
    implementation(Deps.appCompat)
    implementation(Deps.kotlinStdLib)
    implementation(Deps.constraintLayout)
    implementation(Deps.timber)
    implementation(Deps.androidMaterial)
    implementation(Deps.rxAndroid)
    implementation(Deps.rxjava)
    implementation(Deps.daggerAndroid)
    implementation(Deps.viewModel)
    implementation(Deps.viewModelExtensions)
    implementation(Deps.ktxCore)
    implementation(Deps.rxBinding)
    implementation(Deps.permissionDispatcher)
    implementation(Deps.picasso)
    kapt(Deps.permissionDispatcherProcessor)
    kapt(Deps.daggerAndroidCompiler)
    kapt(Deps.daggerCompiler)
    testImplementation(TestDeps.junit4)
    testImplementation(TestDeps.mockito)
    testImplementation(TestDeps.mockitoKotlin)
    testImplementation(TestDeps.mockitoInline)
    androidTestImplementation(TestDeps.testRunner)
    androidTestImplementation(TestDeps.espresso)
}
