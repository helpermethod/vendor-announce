package io.sdkman

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.contentType
import io.ktor.server.application.call
import io.ktor.server.config.MapApplicationConfig
import io.ktor.server.config.mergeWith
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.testing.testApplication

class AnnounceSpec : WordSpec({
    "POST /announce/struct" should {
        "return the payload as response" {
            testApplication {
                /*
                environment {
                    config = config.mergeWith(
                        MapApplicationConfig(
                            "twitter.apiKey" to "",
                            "twitter.apiSecretKey" to "",
                            "twitter.accessToken" to "",
                            "twitter.accessTokenSecret" to "",
                        )
                    )
                }
                 */
                externalServices {
                    hosts("https://api.twitter.com") {
                        routing {
                            post("/2/tweets") {
                                call.respond(
                                    Created,
                                    """
                                    {
                                        "data": {
                                            "edit_history_tweet_ids": [
                                                "1631248723278692357"
                                            ],
                                            "id": "1631248723278692357",
                                            "text": "https://github.com/quarkusio/quarkus/releases/tag/2.7.7.Final quarkus 2.7.7 available on SDKMAN!""
                                        }
                                    }
                                    """.trimIndent()
                                )
                            }
                        }
                    }
                }

                val response =
                    client.post("/announce/struct") {
                        contentType(Application.Json)
                        setBody(
                            """
                            {
                                "candidate": "quarkus",
                                "version": "2.7.7",
                                "url": "https://github.com/quarkusio/quarkus/releases/tag/2.7.7.Final"
                            }
                            """.trimIndent(),
                        )
                    }

                response.status shouldBe Created
            }
        }
    }
})
