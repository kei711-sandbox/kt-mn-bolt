plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32" apply false
    id("org.jetbrains.kotlin.kapt") version "1.4.32" apply false
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32" apply false
    id("com.github.johnrengelman.shadow") version "7.0.0" apply false
    id("io.micronaut.application") version "1.5.0" apply false
    id("io.micronaut.library") version "1.5.0" apply false
}

version = "0.1"
group = "com.example"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")

    repositories {
        mavenCentral()
    }

    dependencies {
        "api"(kotlin("stdlib-jdk8"))
        "api"(kotlin("reflect"))
        "runtimeOnly"("ch.qos.logback:logback-classic")
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    listOf("compileKotlin", "compileTestKotlin").forEach {
        tasks.named<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>(it) {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
}
