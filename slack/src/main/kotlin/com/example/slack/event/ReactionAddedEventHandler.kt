package com.example.slack.event

import com.google.gson.Gson
import com.slack.api.bolt.handler.BoltEventHandler
import com.slack.api.model.event.ReactionAddedEvent
import javax.inject.Singleton

@Singleton
class ReactionAddedEventHandler : EventHandler<ReactionAddedEvent> {
    override val event: Class<ReactionAddedEvent> = ReactionAddedEvent::class.java
    override val handler: BoltEventHandler<ReactionAddedEvent> = BoltEventHandler<ReactionAddedEvent> { payload, ctx ->
        if (payload.event.reaction == "+1") {
            val targetMessage = ctx.client().conversationsReplies {
                it.channel(payload.event.item.channel)
                    .ts(payload.event.item.ts)
            }
            val message = ctx.client().chatPostMessage {
                it.channel(payload.event.item.channel)
                    .threadTs(targetMessage.messages.firstOrNull()?.threadTs ?: payload.event.item.ts)
                    .text("thx :two_hearts:\ntarget: ${targetMessage}\n```${Gson().toJson(payload.event)}```")
            }
            if (!message.isOk) {
                ctx.logger.error("chat.postMessage failed: ${message.error}")
            }
        }
        ctx.ack()
    }
}
