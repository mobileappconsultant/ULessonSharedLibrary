import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    `maven-publish`
    id("kotlinx-serialization")
}

apply {
    plugin("maven-publish")
}

version = file("../VERSION").readText()

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ulessonshared"
            xcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.serialization)
                api(libs.ktor.client)
                api(libs.ktor.client.logging)
                api(libs.ktor.client.serialization)
                api(libs.ktor.client.content.negotiation)
                api(libs.ktor.serialization.kotlinx.json)
                api(libs.multiplatform.settings)
                api(libs.multiplatform.settings.noarg)
                api(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.arkangel.ulessonsharedlibrary"
    compileSdk = 33
    defaultConfig {
        minSdk = 23
    }
}

val githubProperties = Properties()
githubProperties.load(project.rootProject.file("github.properties").inputStream())

publishing {
    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/mobileappconsultant/ulessonsharedlibrary")
            credentials {
                username = githubProperties["gpr.user"] as String? ?: System.getenv("GPR_USER")
                password = githubProperties["gpr.key"] as String? ?: System.getenv("GPR_KEY")
            }
        }
    }

    publications {
        register<MavenPublication>("gpr") {
            groupId = "com.mobileappconsultant.ulessonshared"
            artifactId = "ulessonshared"
            version = file("../VERSION").readText()
            artifact("$buildDir/outputs/aar/ulessonshared-release.aar")

            pom.withXml {
                // for dependencies and exclusions
                val dependenciesNode = asNode().appendNode("dependencies")
                configurations.implementation.get().allDependencies.withType(ModuleDependency::class.java) {
                    val dependencyNode = dependenciesNode.appendNode("dependency")
                    dependencyNode.appendNode("groupId", group)
                    dependencyNode.appendNode("artifactId", name)
                    dependencyNode.appendNode("version", version)

                    // for exclusions
                    if (excludeRules.size > 0) {
                        val exclusions = dependencyNode.appendNode("exclusions")
                        excludeRules.forEach { ex ->
                            val exclusion = exclusions.appendNode("exclusion")
                            exclusion.appendNode("groupId", ex.group)
                            exclusion.appendNode("artifactId", ex.module)
                        }
                    }
                }
            }
        }
    }
}