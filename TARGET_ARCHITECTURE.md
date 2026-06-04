# Target Architecture

## Goals

This repository should evolve into a maintainable Android platform where build logic, shared core capabilities, feature code, domain rules, data access, tests, scripts, and documentation have clear ownership.

The intended direction is inspired by Now in Android, Android Architecture Templates, and large production Android repositories, while staying proportional to the current app size.

## Target top-level layout

```text
root/
├── app/                    # Application shell, manifest, app-level navigation, dependency wiring
├── core/                   # Reusable platform capabilities with no feature ownership
│   └── preferences/        # Shared user settings and local preference APIs
├── feature/                # User-facing features, created only when independently owned
├── data/                   # Repository implementations and data sources, created when needed
├── domain/                 # Pure business rules/models/use cases, created when needed
├── build-logic/            # Gradle convention plugins
├── docs/                   # Contributor and technical documentation
├── scripts/                # Repeatable local/CI helper scripts
├── testing/                # Shared test utilities and fixtures when needed
├── tools/                  # Developer tooling configuration and generated reports
└── .github/                # CI, PR templates, issue templates, code ownership
```

## Module responsibility rules

### `:app`

Owns the Android application artifact. It may contain the launcher activity, manifest, application class, app-wide navigation, and dependency assembly. It should remain thin over time.

Allowed dependencies:

- `:feature:*`
- `:domain:*`
- `:data:*`
- `:core:*`

### `:feature:*`

Owns user-facing screens and feature-specific presentation state. Feature modules should not own shared infrastructure or persistence primitives.

Allowed dependencies:

- `:domain:*`
- `:core:*`

### `:data:*`

Owns repository implementations, local/remote data sources, serialization, and persistence implementation details.

Allowed dependencies:

- `:domain:*`
- `:core:*`

### `:domain:*`

Owns product concepts, business rules, use cases, and repository interfaces. Domain modules should be platform-light where possible.

Allowed dependencies:

- `:core:*`

### `:core:*`

Owns reusable primitives such as preferences, design system, common utilities, logging, dispatchers, and analytics abstractions. Core modules may depend only on other core modules.

Allowed dependencies:

- `:core:*`

## Dependency direction

Dependency direction is always inward and never back toward the app shell:

```text
app → feature → domain → core
app → data → domain → core
app → core
```

The `checkModuleBoundaries` Gradle task enforces this direction for project dependencies.

## Build architecture

- Kotlin DSL is the only Gradle DSL for repository build files.
- `gradle/libs.versions.toml` is the dependency and plugin version source of truth.
- `build-logic` owns Android convention plugins.
- New Android modules should apply either:
  - `androidautobuild.android.application` for the application shell.
  - `androidautobuild.android.library` for Android library modules.
- Build scripts should declare what is unique to the module only: module dependencies, product-specific Android options, and feature-specific libraries.

## Testing strategy

- Unit tests should live beside the module they verify.
- Shared fixtures and rules should move into `testing/` only when they are used by more than one module.
- CI should run architecture checks, unit tests, and a debug build for every pull request.
- Instrumentation tests should be added as features become more interactive and should be separated from fast pull request validation if runtime becomes high.

## Release strategy

- Pull requests run unsigned verification builds.
- Signed APK/AAB generation runs only from manual CI dispatch or a future protected release workflow.
- Release credentials are supplied by GitHub Secrets and must never be committed.
- Release notes, versioning policy, and Play Console promotion should be documented before a production store launch.
