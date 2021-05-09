package com.example.slack.command

import com.slack.api.bolt.App
import com.slack.api.bolt.handler.builtin.SlashCommandHandler

interface Command {
    val name: String
    val handler: SlashCommandHandler
    fun apply(app: App) {
        app.command(this.name, this.handler)
    }
}