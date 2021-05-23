package com.example.slack

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.slack.api.RequestConfigurator
import com.slack.api.Slack
import com.slack.api.SlackConfig
import com.slack.api.app_backend.SlackSignature
import com.slack.api.bolt.App
import com.slack.api.bolt.AppConfig
import com.slack.api.bolt.request.RequestHeaders
import com.slack.api.bolt.util.JsonOps
import com.slack.api.methods.impl.MethodsClientImpl
import com.slack.api.methods.request.auth.AuthTestRequest
import com.slack.api.methods.response.auth.AuthTestResponse
import com.slack.api.util.http.SlackHttpClient


class TestUtil(handlers: List<Handler>) {
    constructor(handler: Handler) : this(listOf(handler))

    private var signingSecret = "test-secret"
    private var botToken = "xoxb-dummy-token"
    private var signatureGenerator = SlackSignature.Generator(signingSecret)
    private val client: SlackHttpClient = mock {
        on { config } doReturn SlackConfig.DEFAULT
    }
    val methods = spy(MethodsClientImpl(client)) {
        onGeneric { authTest(any<RequestConfigurator<AuthTestRequest.AuthTestRequestBuilder>>()) } doReturn AuthTestResponse().apply {
            isOk = true
        }
    }
    private val slack: Slack = spy(Slack()) {
        on { methods() } doReturn methods
        on { methods(null) } doReturn methods
        on { methods(botToken) } doReturn methods
    }
    val app = App(
        AppConfig.builder().slack(slack).signingSecret(signingSecret).singleTeamBotToken(botToken).build()
    ).apply(handlers)

    fun createRequestParams(payload: Any): Params {
        val requestBody: String = JsonOps.toJsonString(payload)
        val timestamp = (System.currentTimeMillis() / 1000).toString()
        val rawHeaders: Map<String, List<String>> = mapOf(
            "Content-Type" to listOf("application/x-www-form-urlencoded"),
            SlackSignature.HeaderNames.X_SLACK_REQUEST_TIMESTAMP to listOf(timestamp),
            SlackSignature.HeaderNames.X_SLACK_SIGNATURE to listOf(signatureGenerator.generate(timestamp, requestBody))
        )
        return Params(requestBody, RequestHeaders(rawHeaders))
    }

    data class Params(
        val requestBody: String,
        val headers: RequestHeaders
    );
}
