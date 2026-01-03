import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    dependencies {
        val detektVersion = "1.23.8" //TODO move it to libs.versions.toml
        //For some reason the version catalog is recommend here, but it doesn't work.
        detekt("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
        detekt("io.gitlab.arturbosch.detekt:detekt-cli:$detektVersion")
    }
}

subprojects {
    tasks.withType<Detekt>().configureEach {
        reports {
            allRules = false // activate all available (even unstable) rules.
            autoCorrect = true // Correct right-away all correctable code smells
            buildUponDefaultConfig = true // preconfigure defaults
            config.setFrom("${project.rootDir}/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
        }
    }
}
