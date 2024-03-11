enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TimeTracker_Pro"

include(
    ":androidApp",
    ":shared",
    ":shared:core",
    ":shared:core:core-common",
    ":shared:core:core-api"
)
