package com.example.app.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/healthz")
class HealthController {
    @Get("/", produces = ["text/plain"])
    fun index(): HttpResponse<String> {
        return HttpResponse.ok("OK")
    }
}
