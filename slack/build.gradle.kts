val boltVersion: String by project

plugins {
    id("io.micronaut.library")
}

micronaut {
    testRuntime("junit5")
}

dependencies {
    api("com.slack.api:bolt:$boltVersion")
    api("com.slack.api:bolt-socket-mode:$boltVersion")
    implementation("com.slack.api:slack-api-model-kotlin-extension:$boltVersion")
    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:1.17")

    testImplementation("io.micronaut:micronaut-http-client")
}
