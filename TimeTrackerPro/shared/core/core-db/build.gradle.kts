plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinPluginSerialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Core-db Module"
        homepage = "Link to the Shared Core-db Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        framework {
            baseName = "core-db"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Core module
                api(projects.shared.core.coreCommon)
                // Sql dependencies
                api(libs.sql.delight.runtime)
                api(libs.sql.delight.coroutine.extentions)
                api(libs.kotlin.date.time)
            }
        }
        val commonTest by getting {
            dependencies {
                api(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.sql.delight.android.driver)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                api(libs.sql.delight.native.driver)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
            dependencies {

            }
        }
    }
}

android {
    namespace = "com.appsdeviser.core_db"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}

sqldelight {
    databases {
        create("TimeTrackerDatabase") {
            packageName.set("com.appsdeviser.core_db.sqldelight")
            sourceFolders.set(listOf("sqldelight"))
        }
    }
}