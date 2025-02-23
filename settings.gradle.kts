pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("com.android.application") version "8.5.2" apply false
        id("com.android.library") version "8.5.2" apply false
        id("org.jetbrains.kotlin.android") version "2.1.10" apply false
        id("org.jetbrains.compose") version "1.6.2" apply false
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "module"
include(":app")
include(":DragAndDrop")
