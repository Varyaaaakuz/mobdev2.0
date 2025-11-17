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
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MovieProject"
include(":app")
include(":data")
include(":domain")

project(":data").projectDir = File("data")
project(":domain").projectDir = File("domain")
include(":scrollviewapp")
include(":listviewapp")
include(":recyclerviewapp")
include(":retrofitapp")
include(":fragmentapp")
include(":fragmentapp2")
include(":fragmentmanagerapp")
include(":resultapifragmentapp")
