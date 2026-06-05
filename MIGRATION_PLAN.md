# Migration Plan

## Phase 1 — Build foundation and first boundary (implemented)

- Convert Gradle build files from Groovy DSL to Kotlin DSL.
- Introduce `gradle/libs.versions.toml` as the dependency version source of truth.
- Add `build-logic` with Android application and Android library convention plugins.
- Create `:core:preferences` for shared preference/theme/language state.
- Move `UserPreferences` from `:app` into `:core:preferences`.
- Remove unused duplicate `ThemePreferences` code.
- Add module-boundary enforcement via `checkModuleBoundaries`.
- Add architecture documentation, CI modernization, PR templates, issue templates, CODEOWNERS, and contributor-oriented repository folders.

## Phase 2 — Thin app shell

- Identify screen groups that are cohesive enough to become feature modules.
- Move feature-specific activities/resources together, one feature at a time.
- Keep `:app` focused on manifest, application class, navigation entry points, and dependency assembly.
- Add screen-level unit tests as code moves.

Suggested candidate features once ownership is clearer:

- `:feature:home`
- `:feature:settings`
- `:feature:saved`
- `:feature:trivia`
- `:feature:content`

## Phase 3 — Domain and data extraction

Create domain/data modules only when there are real abstractions to own:

- Content models and article metadata.
- Quiz/question models and scoring rules.
- Bookmark/favorite repository interfaces.
- Repository implementations backed by preferences, files, Room, network, or generated static data.

Potential modules:

- `:domain:content`
- `:domain:saved-items`
- `:data:preferences`
- `:data:content`

## Phase 4 — Test platform

- Add shared test fixtures only after duplication appears across modules.
- Add architecture tests for dependency rules if Gradle enforcement is not enough.
- Add instrumentation test workflows for high-value user journeys.
- Consider screenshot tests after UI structure stabilizes.

## Phase 5 — Release hardening

- Define semantic versioning and changelog policy.
- Add release notes generation.
- Add Play signing and track-promotion documentation.
- Add dependency update automation.
- Add static analysis once the module structure is stable.

## Migration principles

- Do not create empty Gradle modules just to mirror a fashionable architecture.
- Every module must have a clear owner, clear inputs/outputs, and tests appropriate to its risk.
- Move code in small, buildable steps.
- Prefer deleting dead code over wrapping it in new abstractions.
- Keep dependency direction enforceable through Gradle tasks and documentation.
