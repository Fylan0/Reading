pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Reading"

include(":app")
include(":core")
include(":core:data")
include(":core:ui")
include(":core:database")
include(":feature")
include(":feature:read")
include(":feature:bookshelf")
include(":feature:settings")
include(":core:designsystem")
include(":feature:bookstore")
