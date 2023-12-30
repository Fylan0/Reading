plugins {
    kotlin("kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.android.kotlin.jetbrains)
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.fylan.reading.core.database"
    compileSdk = 33

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

//error： 2 files found with path 'META-INF/gradle/incremental.annotation.processors'
//!!!在 Android 中，implementation 和 kapt 是 Gradle 构建脚本中用于声明依赖的两个不同的关键字。
//
//implementation:
//
//implementation 是用于声明项目在运行时需要使用的依赖库的关键字。
//这些依赖库将包含在生成的 APK 文件中，并在运行时用于应用程序的正常功能。
//例如，对于 Android 应用程序，implementation 通常用于引入一些基础库、UI 库、网络库等，这些库在应用程序运行时被调用。
//
//kapt 是用于声明项目在编译时需要使用的注解处理器（Annotation Processor）的关键字。
//注解处理器通常用于在编译时生成代码，例如生成 Dagger2 的依赖注入代码、Room 数据库的代码等。
//kapt 依赖通常与 implementation 依赖一起使用，因为注解处理器生成的代码通常需要在运行时使用。
dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}