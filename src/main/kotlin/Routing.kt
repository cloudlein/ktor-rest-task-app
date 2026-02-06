package com.example

import com.example.model.TaskRepository
import com.example.model.tasksAsTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    install(StatusPages) {
        exception<IllegalArgumentException> { call, cause ->
            call.respond("App in illegal state as ${cause.message}")
        }
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/test1") {
            val text = "<h1>Hello From Ktor<h1/>"
            val type = ContentType.parse("application/json")
            call.respondText(text, type)
        }
        get("/error-test") {
            throw IllegalArgumentException("Too Busy")
        }
        get("/tasks") {
            val tasks = TaskRepository.allTasks()
            call.respondText(
                contentType = ContentType.parse("text/html"),
                text = tasks.tasksAsTable()
            )
        }

        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
        staticResources("/content", "mycontent")
    }
}
