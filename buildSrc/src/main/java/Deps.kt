object Ver {
    const val kotlin = "1.3.40"
    const val androidGradlePluginVersion = "3.4.2"
    const val detekt = "1.0.0-RC14"
    const val appCompat = "1.1.0-beta01"
    const val androidMaterial = "1.0.0-rc01"
    const val moshi = "1.8.0"
    const val rxjava = "2.2.10"
    const val rxAndroid = "2.1.1"
    const val ktx = "1.1.0-rc02"
    const val dagger = "2.23.2"
    const val constraintLayout = "1.1.3"
    const val viewModel = "2.0.0"
    const val threeTenAbp = "1.2.1"
    const val timber = "4.7.1"
    const val rxBinding = "3.0.0-alpha2"
    const val retrofit = "2.6.0"
    const val picasso  = "2.71828"
    const val permissionDispatcher = "4.5.0"
}

object TestVer {
    const val junit4 = "4.12"
    const val testRunner = "1.1.1"
    const val espresso = "3.1.1"
    const val mockitoKotlin = "2.1.0"
    const val mockito = "3.0.0"
}

object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Ver.androidGradlePluginVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Ver.kotlin}"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val detekt = "io.gitlab.arturbosch.detekt"
}

object AndroidSdk {
    const val min = 21
    const val compile = 28
    const val target = compile
}

object Deps {
    const val appCompat = "androidx.appcompat:appcompat:${Ver.appCompat}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Ver.kotlin}"
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Ver.rxjava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Ver.rxAndroid}"
    const val ktxCore = "androidx.core:core-ktx:${Ver.ktx}"
    const val dagger = "com.google.dagger:dagger:${Ver.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:${Ver.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Ver.dagger}"
    const val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:${Ver.dagger}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Ver.constraintLayout}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Ver.viewModel}"
    const val viewModelExtensions = "androidx.lifecycle:lifecycle-extensions:${Ver.viewModel}"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Ver.moshi}"
    const val moshiConverterForRetrofit = "com.squareup.retrofit2:converter-moshi:${Ver.retrofit}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Ver.moshi}"

    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Ver.threeTenAbp}"
    const val rxBinding = "com.jakewharton.rxbinding3:rxbinding-core:${Ver.rxBinding}"
    const val timber = "com.jakewharton.timber:timber:${Ver.timber}"
    const val androidMaterial = "com.google.android.material:material:${Ver.androidMaterial}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Ver.retrofit}"
    const val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Ver.retrofit}"
    const val permissionDispatcher =  "org.permissionsdispatcher:permissionsdispatcher:${Ver.permissionDispatcher}"
    const val permissionDispatcherProcessor =  "org.permissionsdispatcher:permissionsdispatcher-processor:${Ver.permissionDispatcher}"
    const val picasso = "com.squareup.picasso:picasso:${Ver.picasso}"

}


object TestDeps {
    const val junit4 = "junit:junit:${TestVer.junit4}"
    const val testRunner = "androidx.test:runner:${TestVer.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${TestVer.espresso}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${TestVer.mockitoKotlin}"
    const val mockitoInline = "org.mockito:mockito-inline:${TestVer.mockito}"
    const val mockito = "org.mockito:mockito-core:${TestVer.mockito}"
}
