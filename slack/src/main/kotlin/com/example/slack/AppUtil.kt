package com.example.slack

import com.slack.api.bolt.App

fun App.apply(handlers: List<Handler>): App {
    handlers.forEach { handler -> handler.apply(this) }
    return this
}
