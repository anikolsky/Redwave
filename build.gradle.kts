buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
//        classpath("")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    }
}

//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir())
//}

//plugins {
//    id 'com.android.application' version '8.0.0' apply false
//    id 'com.android.library' version '8.0.0' apply false
//    id 'org.jetbrains.kotlin.android' version '1.8.20' apply false
//}