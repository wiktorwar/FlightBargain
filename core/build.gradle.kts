plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
}
android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("proguard-rules.pro")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    api(project(":model"))
    implementation(Deps.kotlinStdLib)
    implementation(Deps.rxjava)
    implementation(Deps.rxAndroid)
    implementation(Deps.timber)
    implementation(Deps.dagger)
    implementation(Deps.moshiConverterForRetrofit)
    api(Deps.retrofit)
    api(Deps.retrofitRxJavaAdapter)
    kapt(Deps.daggerAndroidCompiler)
    kapt(Deps.daggerCompiler)
    testImplementation(TestDeps.junit4)
    androidTestImplementation(TestDeps.testRunner)
    androidTestImplementation(TestDeps.espresso)
}
