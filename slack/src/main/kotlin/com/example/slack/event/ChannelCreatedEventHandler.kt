package com.example.slack.event

import com.example.slack.ChannelEventConfig
import com.slack.api.bolt.handler.BoltEventHandler
import com.slack.api.model.event.ChannelCreatedEvent
import javax.inject.Singleton

@Singleton
class ChannelCreatedEventHandler(config: ChannelEventConfig) : EventHandler<ChannelCreatedEvent> {
    override val event: Class<ChannelCreatedEvent> = ChannelCreatedEvent::class.java
    override val handler: BoltEventHandler<ChannelCreatedEvent> = BoltEventHandler<ChannelCreatedEvent> { req, ctx ->
        ctx.client().chatPostMessage {
            it.channel(config.notification.channelId)
                .username(config.notification.userName)
                .text("<#${req.event.channel.id}> が作成されました")
        }
        ctx.ack()
    }
}
