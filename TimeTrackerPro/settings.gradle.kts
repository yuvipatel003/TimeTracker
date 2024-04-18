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
include(":androidApp")
include(":shared")
include(":shared:core")
include(":shared:core:core-common")
include(":shared:core:core-api")
include(":shared:core:core-db")
include(":shared:onboarding")
include(":shared:tracker")
include(":shared:settings")
