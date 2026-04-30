# AndroidAutoBuildAPK

A lightweight Android project that demonstrates:

- A simple multi-page app structure (home + text content pages).
- Automatic APK builds with GitHub Actions.
- Signed **release APK** generation in CI using GitHub Secrets.
- Automatic `versionCode` / `versionName` incrementing per workflow run.

---

## App (Current State)

The app now includes:

- `MainActivity` as home page.
- Navigation buttons for:
  - About
  - Settings
  - Rules
  - Terms
- Separate activities for each page:
  - `AboutActivity`
  - `SettingsActivity`
  - `RulesActivity`
  - `TermsActivity`
- A shared scrollable text layout (`activity_text_page.xml`) used by all text pages.
- Text content stored in `app/src/main/res/values/strings.xml`.

This keeps the app minimal, offline-friendly, and easy to extend.

---

## CI/CD Workflow

Workflow file: `.github/workflows/build.yml`

On `push`, `pull_request`, or manual trigger, CI does the following:

1. Checks out source code.
2. Sets up JDK 17.
3. Sets up Android SDK.
4. Builds debug APK (`assembleDebug`).
5. Decodes the signing keystore from `KEYSTORE_BASE64` into `keystore.jks`.
6. Sets dynamic version values:
   - `versionCode = github.run_number`
   - `versionName = 1.<github.run_number>`
7. Builds signed release APK (`assembleRelease`) with signing + version properties.
8. Uploads artifacts:
   - `app-debug`
   - `app-release-signed`

---

## Required GitHub Secrets

To produce signed release APKs in CI, configure these repository secrets:

- `KEYSTORE_BASE64`
- `KEYSTORE_PASSWORD`
- `KEY_ALIAS`
- `KEY_PASSWORD`

### How to create `KEYSTORE_BASE64`

Base64-encode your `keystore.jks` file locally and copy the output into the secret:

```bash
base64 -w 0 keystore.jks
```

> On macOS, use:
>
> ```bash
> base64 keystore.jks | tr -d '\n'
> ```

---

## Versioning Behavior

`app/build.gradle` reads optional Gradle project properties:

- `appVersionCode`
- `appVersionName`

If provided (as in CI), they override defaults. Otherwise it falls back to:

- `versionCode 1`
- `versionName "1.0"`

This means local development remains simple, while CI builds are uniquely versioned.

---

## Build Locally

```bash
chmod +x gradlew
./gradlew assembleDebug
```

To build release locally with custom version values:

```bash
./gradlew assembleRelease \
  -PappVersionCode=123 \
  -PappVersionName=1.123
```

---

## Project Goal

This repository is evolving into a simple, maintainable offline encyclopedia app (starting with Hokm-related text content), while preserving a practical production-style Android CI pipeline.
