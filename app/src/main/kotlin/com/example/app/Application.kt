package com.example.app

import com.slack.api.bolt.socket_mode.SocketModeApp
import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    val app = build()
        .args(*args)
        .packages("com.example")
        .banner(false)
        .start()

    app.use {
        val socketModeApp = it.getBean(SocketModeApp::class.java)
        socketModeApp.start()
    }
}

