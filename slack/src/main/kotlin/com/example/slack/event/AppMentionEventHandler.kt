package com.example.slack.event

import com.slack.api.bolt.handler.BoltEventHandler
import com.slack.api.model.event.AppMentionEvent
import javax.inject.Singleton

@Singleton
class AppMentionEventHandler : EventHandler<AppMentionEvent> {
    override val event: Class<AppMentionEvent> = AppMentionEvent::class.java
    override val handler: BoltEventHandler<AppMentionEvent> = BoltEventHandler<AppMentionEvent> { payload, ctx ->
        val message = ctx.client().chatPostMessage {
            it.channel(payload.event.channel)
                .threadTs(payload.event.threadTs)
                .text("Hi <@${payload.event.user}>! ${payload.event.text}")
        }
        if (!message.isOk) {
            ctx.logger.error("chat.postMessage failed: ${message.error}")
        }
        ctx.ack()
    }
}
