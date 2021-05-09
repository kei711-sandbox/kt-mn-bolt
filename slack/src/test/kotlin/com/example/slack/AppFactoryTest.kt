package com.example.slack

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
internal class AppFactoryTest(private val embeddedServer: EmbeddedServer) {
    @Test
    fun testSlackEndpoint() {
        val client = embeddedServer.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.url)
        val res = client.toBlocking().exchange(HttpRequest.POST("/slack/events", "token=random&ssl_check=1"), String::class.java)
        assertEquals(HttpStatus.OK, res.status())
        client.close()
    }
}
