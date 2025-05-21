    plugins {
    id("java-library")
    alias(libs.plugins.kotlin.library)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":common"))
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockk)

    //coroutine
    implementation(libs.coroutines.android)
}
