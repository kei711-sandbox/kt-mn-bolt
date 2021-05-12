package com.example.slack.event

import com.example.slack.ChannelEventConfig
import com.slack.api.bolt.handler.BoltEventHandler
import com.slack.api.model.event.ChannelRenameEvent
import javax.inject.Singleton

@Singleton
class ChannelRenameEventHandler(config: ChannelEventConfig) : EventHandler<ChannelRenameEvent> {
    override val event: Class<ChannelRenameEvent> = ChannelRenameEvent::class.java
    override val handler: BoltEventHandler<ChannelRenameEvent> = BoltEventHandler<ChannelRenameEvent> { req, ctx ->
        ctx.client().chatPostMessage {
            it.channel(config.notification.channelId)
                .username(config.notification.userName)
                .text("<#${req.event.channel.id}> にリネームされました")
        }
        ctx.ack()
    }
}
