# ULesson Shared Library

## Android Set Up

Create a `github.properties` file in the project root with the following contents:

```properties
gpr.user=mobileappconzultant@gmail.com
gpr.key=${GENERATED_GITHUB_ACCESS_TOKEN}
```

Replace `${GENERATED_GITHUB_ACCESS_TOKEN}` with the generated GitHub access token.

Next, navigate to `settings.gradle` (or `settings.gradle.kts`) and add this block:
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // ... other repositories
        def githubProperties = new Properties()
        githubProperties.load(new FileInputStream(file("github.properties")))
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/mobileappconsultant/ulessonsharedlibrary")
            credentials {
                username = githubProperties["gpr.user"] as String? ?: System.getenv("GPR_USER")
                password = githubProperties["gpr.key"] as String? ?: System.getenv("GPR_KEY")
            }
        }
    }
}
```

Inside the app-level `build.gradle(.kts)` file, add the library dependency:
```groovy
dependencies {
    // ... other dependencies
    implementation ("com.mobileappconsultant.ulessonsharedlibrary:ulessonshared:0.0.1")
}
```

Sync project and use.

## iOS Set Up

Run the following shell script on Terminal:
```shell
./publish_ios.sh
```

Add the project to the iOS project via Swift Packages
