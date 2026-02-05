package com.example

import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/test1"){
            val text = "<h1>Hello From Ktor<h1/>"
            val type = ContentType.parse("application/json")
            call.respondText(text, type)
        }

        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
        staticResources("/content", "mycontent")
    }
}
