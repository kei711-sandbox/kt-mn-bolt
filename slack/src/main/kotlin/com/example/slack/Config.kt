package com.example.slack

import io.micronaut.context.annotation.ConfigurationBuilder
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("slack.channel-event")
class ChannelEventConfig {
    @ConfigurationBuilder(configurationPrefix = "notification")
    val notification = Notification()
}

class Notification {
    lateinit var channelId: String
    lateinit var userName: String
}

