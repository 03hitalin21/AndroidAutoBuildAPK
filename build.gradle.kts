import org.gradle.api.artifacts.ProjectDependency

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

val moduleDependencyRules = mapOf(
    "app" to setOf("feature", "domain", "data", "core"),
    "feature" to setOf("domain", "core"),
    "data" to setOf("domain", "core"),
    "domain" to setOf("core"),
    "core" to setOf("core"),
)

tasks.register("checkModuleBoundaries") {
    group = "verification"
    description = "Validates architectural dependency rules between Gradle modules."

    doLast {
        val violations = mutableListOf<String>()

        allprojects.forEach { sourceProject ->
            val sourceLayer = sourceProject.path.layerName()
            sourceProject.configurations.forEach { configuration ->
                configuration.dependencies.withType<ProjectDependency>().forEach { dependency ->
                    val targetPath = dependency.dependencyProject.path
                    val targetLayer = targetPath.layerName()
                    val allowedTargets = moduleDependencyRules[sourceLayer].orEmpty()

                    val isSameProject = sourceProject.path == targetPath
                    val isAllowed = targetLayer in allowedTargets
                    if (!isSameProject && !isAllowed) {
                        violations += "${sourceProject.path} -> $targetPath via ${configuration.name} is not allowed. " +
                            "Layer '$sourceLayer' may depend on: ${allowedTargets.sorted().joinToString()}"
                    }
                }
            }
        }

        check(violations.isEmpty()) {
            "Module boundary violations found:\n" + violations.joinToString(separator = "\n")
        }
    }
}

allprojects {
    tasks.matching { it.name == "check" }.configureEach {
        dependsOn(rootProject.tasks.named("checkModuleBoundaries"))
    }
}

fun String.layerName(): String = trimStart(':').substringBefore(':').ifBlank { "root" }
