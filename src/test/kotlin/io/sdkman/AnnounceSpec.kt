package io.sdkman

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.core.spec.style.WordSpec
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication

class AnnounceSpec : WordSpec({
    "POST /announce/struct" should {
        "return the payload as response" {
            testApplication {
                val response =
                    client.post("/announce/struct") {
                        contentType(ContentType.Application.Json)
                        setBody(
                            """
                            {
                                "candidate": "quarkus",
                                "version": "2.7.7",
                                "hashtag": "",
                                "url": "https://github.com/quarkusio/quarkus/releases/tag/2.7.7.Final"
                            }
                            """,
                        )
                    }

                response shouldHaveStatus HttpStatusCode.OK
            }
        }
    }
})
