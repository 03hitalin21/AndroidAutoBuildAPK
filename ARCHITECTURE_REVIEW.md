# Architecture Review

## Current repository structure before this phase

The repository started as a single Android application module with Groovy Gradle files at the root and in `app/`. Application code, preferences, UI activities, resources, tests, signing-aware release configuration, and CI assumptions were all owned by the `app` module. The only Gradle module boundary was `:app`, so every Kotlin class lived in one package and could call every other class directly.

```text
root/
├── app/
│   ├── build.gradle
│   └── src/
│       ├── main/java/com/hokmencyclopedia/app/
│       ├── main/res/
│       ├── test/
│       └── androidTest/
├── build.gradle
├── settings.gradle
├── gradle.properties
└── .github/workflows/build.yml
```

## Findings

### Architectural weaknesses

- The application was a single-module app. This is workable for a prototype, but it does not create enforceable seams between UI, preferences, domain rules, data access, test utilities, and future features.
- Activities contain presentation logic, navigation, persistence calls, and static content wiring in the same layer. This makes each screen easy to start with, but hard to test and hard to change safely as features grow.
- `UserPreferences` was stored in the application package and was directly reachable from every screen. Because it was not behind a module boundary, future persistence decisions could leak further into UI code.
- `ThemePreferences` duplicated older preference behavior and was not referenced by the app, creating dead code and a confusing source of truth.
- The manifest used the default sample-oriented package/application namespace, which should be renamed in a later release-planning phase once product identity is finalized.

### Coupling issues

- UI screens were coupled directly to shared preferences via `UserPreferences` rather than a dedicated preferences/settings module.
- The app module owned all dependency declarations, all Android defaults, and all versioning behavior. Any new module would have needed copy-pasted Gradle configuration.
- CI only understood one module and jumped directly to release signing, which increases risk when contributors open pull requests that should only need verification builds.

### Scalability risks

- New features would likely be added as more activities and helpers in the same package, increasing merge conflicts and reducing code ownership clarity.
- There were no documented dependency rules, so future modules could accidentally depend on each other in cycles or bypass intended architectural layers.
- No repository-level documentation existed for the target architecture, migration strategy, contribution workflow, or release workflow.
- The repository lacked issue templates, pull request templates, and code ownership metadata, making multi-contributor work inconsistent.

### Build-system problems

- Gradle files used Groovy DSL rather than Kotlin DSL, reducing type safety and making convention-plugin migration harder.
- Android Gradle Plugin, Kotlin, AndroidX, and test dependency versions were embedded directly in build files instead of a version catalog.
- Build defaults such as SDK versions, Java compatibility, Kotlin JVM target, instrumentation runner, and test dependencies were not centralized.
- There was no architectural verification task to prevent future module-boundary violations.

### Naming and folder organization issues

- The root project name and application id still look like an automation/sample project rather than a product-oriented Android platform.
- All app Kotlin sources were in one package, so package names did not communicate responsibility.
- There were no top-level `core/`, `feature/`, `data/`, `domain/`, `testing/`, `tools/`, `scripts/`, or `docs/` areas to guide future contributors.

## Phase-1 decisions implemented

### 1. Introduce build logic first

The first production-grade step is to centralize Gradle behavior before adding many modules. This repository now uses an included `build-logic` build with Android application and Android library convention plugins. That prevents future modules from copying SDK, Kotlin, Java, and test setup.

### 2. Adopt Kotlin DSL and version catalogs

The root, settings, app, core module, and build-logic Gradle files now use Kotlin DSL. Dependency and plugin versions are centralized in `gradle/libs.versions.toml` so dependency updates have one obvious entry point.

### 3. Create only one clear module in phase 1

The first extracted module is `:core:preferences` because preferences are already shared platform capability, are currently used by multiple screens, and are not feature-specific. Feature, data, and domain directories are documented but not registered as Gradle modules yet because creating empty modules would add build overhead without a clear responsibility.

### 4. Add module-boundary enforcement

A root verification task now validates project dependency directions. The initial rules allow app to depend inward on core/data/domain/feature, feature to depend on domain/core, data to depend on domain/core, domain to depend on core, and core modules only to depend on other core modules.

### 5. Professionalize collaboration and CI/CD

The GitHub Actions workflow now separates verification from signed release artifact production. Pull requests run fast validation, while signed APK/AAB generation is guarded behind manual dispatch and expected repository secrets. Templates and ownership files make contribution expectations explicit.

## Remaining risks after phase 1

- UI still lives in `:app`; feature modules should be extracted only when a feature has enough code and tests to justify ownership.
- Domain models and repositories do not exist yet; extracting them prematurely would create abstract modules without business value.
- The package/application id still needs product naming decisions.
- Release signing still depends on repository secrets; this is acceptable, but the team should document key rotation and Play Console ownership before production launch.
