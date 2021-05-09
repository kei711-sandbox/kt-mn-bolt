package com.example.slack.command

import com.slack.api.bolt.handler.builtin.SlashCommandHandler
import javax.inject.Singleton

@Singleton
class HelloCommand : Command {
    override val name = "/hello"
    override val handler = SlashCommandHandler { _, context ->
        context.respond("hello")
        context.ack()
    }
}
