package com.example.slack

import com.slack.api.bolt.App
import com.slack.api.bolt.AppConfig
import com.slack.api.bolt.socket_mode.SocketModeApp
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class AppFactory {

    @Singleton
    fun appConfig() = AppConfig()

    @Singleton
    fun app(config: AppConfig, handlers: List<Handler>) = App(config).apply(handlers)

    @Singleton
    fun socketModeApp(app: App) = SocketModeApp(app)
}
