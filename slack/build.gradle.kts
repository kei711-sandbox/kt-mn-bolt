val boltVersion: String by project
val mockitoKotlinVersion: String by project
val kotlinxSerializationVersion: String by project

plugins {
    id("io.micronaut.library")
    kotlin("plugin.serialization")
}

micronaut {
    testRuntime("junit5")
}

dependencies {
    api("com.slack.api:bolt:$boltVersion")
    api("com.slack.api:bolt-socket-mode:$boltVersion")
    implementation("com.slack.api:slack-api-model-kotlin-extension:$boltVersion")
    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:1.17")

    implementation ("com.google.code.gson:gson:2.8.6")

    testImplementation("io.micronaut:micronaut-http-client")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion")
}
