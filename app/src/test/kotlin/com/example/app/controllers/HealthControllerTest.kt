package com.example.app.controllers

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
internal class HealthControllerTest(private val embeddedServer: EmbeddedServer)  {
    @Test
    fun testIndex() {
        val client = embeddedServer.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.url)
        val res = client.toBlocking().exchange("/healthz", String::class.java)
        assertEquals(HttpStatus.OK, res.status())
        assertEquals("OK", res.body())
        client.close()
    }
}
