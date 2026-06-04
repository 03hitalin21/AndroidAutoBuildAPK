import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<AppExtension> {
                configureAndroidDefaults(this, includeInstrumentationRunner = true)

                defaultConfig {
                    applicationId = "com.example.androidautobuildapk"
                    targetSdk = libsVersion("targetSdk").toInt()

                    val versionCodeProp = findProperty("appVersionCode")
                    val versionNameProp = findProperty("appVersionName")
                    versionCode = versionCodeProp?.toString()?.toInt() ?: 1
                    versionName = versionNameProp?.toString() ?: "1.0"
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro",
                        )
                    }
                }
            }

            dependencies.add("testImplementation", libsDependency("junit4").get())
            dependencies.add("androidTestImplementation", libsDependency("androidx-test-junit").get())
            dependencies.add("androidTestImplementation", libsDependency("androidx-test-espresso-core").get())
        }
    }
}

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                configureAndroidDefaults(this, includeInstrumentationRunner = false)
            }

            dependencies.add("testImplementation", libsDependency("junit4").get())
            dependencies.add("androidTestImplementation", libsDependency("androidx-test-junit").get())
            dependencies.add("androidTestImplementation", libsDependency("androidx-test-espresso-core").get())
        }
    }
}

private fun Project.configureAndroidDefaults(
    extension: BaseExtension,
    includeInstrumentationRunner: Boolean,
) = extension.apply {
    compileSdkVersion(libsVersion("compileSdk").toInt())

    defaultConfig {
        minSdk = libsVersion("minSdk").toInt()
        if (includeInstrumentationRunner) {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = libsVersion("java")
        }
    }
}

private fun Project.libsVersion(alias: String): String = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")
    .findVersion(alias)
    .get()
    .requiredVersion

private fun Project.libsDependency(alias: String) = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")
    .findDependency(alias)
