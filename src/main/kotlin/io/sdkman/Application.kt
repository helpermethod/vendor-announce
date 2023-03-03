package io.sdkman

import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.sdkman.twitter.AccessToken
import io.sdkman.twitter.AccessTokenSecret
import io.sdkman.twitter.ApiKey
import io.sdkman.twitter.ApiSecretKey
import io.sdkman.twitter.TwitterClient
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation as ServerContentNegotiation

fun main(args: Array<String>) =
    io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(ServerContentNegotiation) {
        json()
    }

    val announceService = AnnounceService(createTwitterClient(environment))

    routing {
        post("/announce/struct") {
            val (candidate, version, url) = call.receive<AnnounceRequest>()

            announceService.announce(candidate, version, url)

            call.response.status(Created)
        }
    }
}

private fun createTwitterClient(environment: ApplicationEnvironment) =
    environment.config.run {
        TwitterClient(
            ApiKey(property("twitter.apiKey").getString()),
            ApiSecretKey(property("twitter.apiSecretKey").getString()),
            AccessToken(property("twitter.accessToken").getString()),
            AccessTokenSecret(property("twitter.accessTokenSecret").getString()),
        )
    }
