# Contributing

## Local setup

1. Install Android Studio with the Android SDK.
2. Use the repository Gradle wrapper.
3. Run verification before opening a pull request:

```bash
./gradlew checkModuleBoundaries testDebugUnitTest assembleDebug
```

## Development rules

- Use Kotlin DSL for Gradle build files.
- Add dependency versions to `gradle/libs.versions.toml`.
- Put shared Gradle behavior in `build-logic` convention plugins.
- Do not add a Gradle module unless it has a clear responsibility.
- Respect dependency direction from `TARGET_ARCHITECTURE.md`.
- Keep the app module thin when extracting future features.

## Pull request expectations

- Explain the motivation and user/developer impact.
- Include tests or explain why no automated test applies.
- Update architecture docs when changing module boundaries.
- Avoid mixing large refactors with feature behavior changes.
