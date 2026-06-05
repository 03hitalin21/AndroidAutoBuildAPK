plugins {
    id("androidautobuild.android.application")
}

dependencies {
    implementation(project(":core:preferences"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.androidx.constraintlayout)
}
