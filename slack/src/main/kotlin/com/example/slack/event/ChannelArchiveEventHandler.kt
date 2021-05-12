package com.example.slack.event

import com.example.slack.ChannelEventConfig
import com.slack.api.bolt.handler.BoltEventHandler
import com.slack.api.model.event.ChannelArchiveEvent
import javax.inject.Singleton

@Singleton
class ChannelArchiveEventHandler(config: ChannelEventConfig) : EventHandler<ChannelArchiveEvent> {
    override val event: Class<ChannelArchiveEvent> = ChannelArchiveEvent::class.java
    override val handler: BoltEventHandler<ChannelArchiveEvent> = BoltEventHandler<ChannelArchiveEvent> { req, ctx ->
        ctx.client().chatPostMessage {
            it.channel(config.notification.channelId)
                .username(config.notification.userName)
                .text("<#${req.event.channel}> がアーカイブされました")
        }
        ctx.ack()
    }
}
