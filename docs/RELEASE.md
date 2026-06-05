# Release Workflow

Signed release artifacts are produced by GitHub Actions through manual workflow dispatch.

## Required secrets

- `KEYSTORE_BASE64`
- `KEYSTORE_PASSWORD`
- `KEY_ALIAS`
- `KEY_PASSWORD`

## Local debug build

```bash
./gradlew assembleDebug
```

## CI release build

Use the `Android CI` workflow with `workflow_dispatch`. The workflow sets a CI-derived version code and version name, decodes the signing keystore from secrets, builds APK/AAB release artifacts, and uploads them as workflow artifacts.

## Security notes

- Never commit keystores, passwords, or Play Console credentials.
- Rotate signing credentials according to the team's release policy.
- Restrict release workflow access through GitHub branch/environment protection before production launch.
