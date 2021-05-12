package com.example.slack.event

import com.example.slack.Handler
import com.slack.api.bolt.App
import com.slack.api.bolt.handler.BoltEventHandler

interface EventHandler<E> : Handler where E : com.slack.api.model.event.Event {
    val event: Class<E>
    val handler: BoltEventHandler<E>
    override fun apply(app: App) {
        app.event(this.event, this.handler)
    }
}
