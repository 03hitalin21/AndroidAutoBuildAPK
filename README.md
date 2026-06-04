# AndroidAutoBuildAPK

AndroidAutoBuildAPK is evolving from a small single-module Android app into a production-grade, contributor-friendly Android platform project.

The app remains a lightweight offline Android experience, while the repository now has the foundations needed for long-term scalability: Kotlin DSL, version catalogs, Gradle convention plugins, module-boundary rules, architecture documentation, and CI workflows that separate pull-request verification from signed release builds.

## Repository layout

```text
root/
├── app/                    # Android application shell and current UI screens
├── core/preferences/       # Shared user preference, theme, language, bookmark, and favorite state
├── feature/                # Future feature modules when ownership is clear
├── data/                   # Future data/repository implementation modules
├── domain/                 # Future domain model/use-case modules
├── build-logic/            # Gradle convention plugins
├── docs/                   # Contributor and release documentation
├── scripts/                # Repeatable helper scripts
├── testing/                # Shared test utilities when needed
├── tools/                  # Developer tooling support files
└── .github/                # CI, templates, and code ownership
```

Only `:app` and `:core:preferences` are registered Gradle modules today. The other directories document where future responsibilities belong without adding empty modules.

## Architecture documents

- [`ARCHITECTURE_REVIEW.md`](ARCHITECTURE_REVIEW.md) — current-state audit, risks, and phase-1 decisions.
- [`TARGET_ARCHITECTURE.md`](TARGET_ARCHITECTURE.md) — intended module responsibilities and dependency rules.
- [`MIGRATION_PLAN.md`](MIGRATION_PLAN.md) — phased migration path from the current app to a scalable platform.
- [`docs/CONTRIBUTING.md`](docs/CONTRIBUTING.md) — local development and pull request expectations.
- [`docs/RELEASE.md`](docs/RELEASE.md) — release workflow and signing guidance.

## Build system

This repository uses:

- Kotlin DSL Gradle build files.
- Version catalogs in `gradle/libs.versions.toml`.
- Included build convention plugins in `build-logic/`.
- A module-boundary verification task: `checkModuleBoundaries`.

## Build locally

```bash
chmod +x gradlew
./gradlew checkModuleBoundaries testDebugUnitTest assembleDebug
```

To build a release locally with custom version values:

```bash
./gradlew assembleRelease \
  -PappVersionCode=123 \
  -PappVersionName=1.123
```

## CI/CD

Workflow file: `.github/workflows/build.yml`

Pull requests and pushes run:

1. Module-boundary verification.
2. Unit tests.
3. Debug APK build.
4. Debug artifact upload.

Signed release APK/AAB generation is available through manual workflow dispatch with `build_release=true`.

## Required GitHub Secrets for signed releases

- `KEYSTORE_BASE64`
- `KEYSTORE_PASSWORD`
- `KEY_ALIAS`
- `KEY_PASSWORD`

### Create `KEYSTORE_BASE64`

```bash
base64 -w 0 keystore.jks
```

On macOS:

```bash
base64 keystore.jks | tr -d '\n'
```

## Project direction

The project will stay buildable throughout migration. Future modules should be introduced only when they have a clear responsibility, clear dependency direction, and tests appropriate to their risk.
