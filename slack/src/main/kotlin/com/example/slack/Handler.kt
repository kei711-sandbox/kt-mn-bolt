package com.example.slack

import com.slack.api.bolt.App

interface Handler {
    fun apply(app: App)
}
