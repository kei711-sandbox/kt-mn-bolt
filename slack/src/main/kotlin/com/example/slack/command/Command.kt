package com.example.slack.command

import com.example.slack.Handler
import com.slack.api.bolt.App
import com.slack.api.bolt.handler.builtin.SlashCommandHandler

interface Command : Handler {
    val name: String
    val handler: SlashCommandHandler
    override fun apply(app: App) {
        app.command(this.name, this.handler)
    }
}
