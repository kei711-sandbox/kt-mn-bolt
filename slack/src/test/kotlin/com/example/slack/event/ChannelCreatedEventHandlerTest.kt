package com.example.slack.event

import com.example.slack.TestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.slack.api.app_backend.events.payload.ChannelCreatedPayload
import com.slack.api.bolt.request.builtin.EventRequest
import com.slack.api.bolt.response.Response
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import com.slack.api.methods.response.chat.ChatPostMessageResponse
import com.slack.api.model.event.ChannelCreatedEvent
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor


@MicronautTest
internal class ChannelCreatedEventHandlerTest(private val createdEventHandler: ChannelCreatedEventHandler) {
    @Test
    fun testChannelCreated() {
        val util = TestUtil(createdEventHandler)
        doReturn(mock<ChatPostMessageResponse>()).whenever(util.methods).chatPostMessage(any<ChatPostMessageRequest>())

        val payload = ChannelCreatedPayload().apply {
            event = ChannelCreatedEvent().apply {
                channel = ChannelCreatedEvent.Channel().apply { id = "C123" }
            }
        }
        val params = util.createRequestParams(payload)
        val req = EventRequest(params.requestBody, params.headers)
        val response: Response = util.app.run(req)
        assertEquals(200, response.statusCode)

        val messageCaptor = ArgumentCaptor.forClass(ChatPostMessageRequest::class.java)
        verify(util.methods, times(1)).chatPostMessage(capture(messageCaptor))
        assertEquals("test-user-name", messageCaptor.value.username)
        assertEquals("test-channel-id", messageCaptor.value.channel)
        assertEquals("<#C123> が作成されました", messageCaptor.value.text)
    }
}
