package io.sdkman.routes

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.routing

fun Application.configureRouting() =
    routing {
        post<Announce.Struct> {
            val structuredAnnounce = call.receive<StructuredAnnounce>()

            call.respond(structuredAnnounce)
        }
    }
