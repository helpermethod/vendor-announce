package io.sdkman

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.sdkman.twitter.TwitterClient
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation as ServerContentNegotiation

fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(ServerContentNegotiation) {
        json()
    }

    val announceService = AnnounceService(createTwitterClient(environment))

    routing {
        post("/announce/struct") {
            val announceRequest = call.receive<AnnounceRequest>()

            announceService.announce(announceRequest)

            call.respond(announceRequest)
        }
    }
}

private fun createTwitterClient(environment: ApplicationEnvironment) =
    environment.config.run {
        TwitterClient(
            apiKey = property("twitter.apiKey").getString(),
            apiSecretKey = property("twitter.apiSecretKey").getString(),
            accessToken = property("twitter.accessToken").getString(),
            accessTokenSecret = property("twitter.accessTokenSecret").getString(),
        )
    }
