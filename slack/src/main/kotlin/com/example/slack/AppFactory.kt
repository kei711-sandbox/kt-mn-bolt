package com.example.slack

import com.slack.api.bolt.App
import com.slack.api.bolt.AppConfig
import com.slack.api.bolt.socket_mode.SocketModeApp
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class AppFactory {

    @Singleton
    fun createAppConfig(): AppConfig {
        return AppConfig()
    }

    @Singleton
    fun createApp(config: AppConfig, handlers: List<Handler>): App {
        val app = App(config)
        handlers.forEach { handler -> handler.apply(app) }
        return app
    }

    @Singleton
    fun socketModeApp(app: App) = SocketModeApp(app)
}
