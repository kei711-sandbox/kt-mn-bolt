package com.example.slack

import com.example.slack.command.Command
import com.slack.api.bolt.App
import com.slack.api.bolt.AppConfig
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton


@Factory
class AppFactory {

    @Singleton
    fun createAppConfig(): AppConfig {
        return AppConfig()
    }

    @Singleton
    fun createApp(config: AppConfig, commands: List<Command>): App {
        val app = App(config)
        commands.forEach { command -> command.apply(app) }
        return app
    }
}
