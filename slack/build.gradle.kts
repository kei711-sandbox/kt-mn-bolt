val boltVersion: String by project

plugins {
    id("io.micronaut.library")
}

micronaut {
    testRuntime("junit5")
}

dependencies {
    api("com.slack.api:bolt-micronaut:$boltVersion")
}
