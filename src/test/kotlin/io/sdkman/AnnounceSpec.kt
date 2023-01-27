package io.sdkman

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.core.spec.style.ShouldSpec
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import io.sdkman.routes.StructuredAnnounce

class AnnounceSpec : ShouldSpec({
    context("POST /announce/struct") {
        should("return the payload as response") {
            testApplication {
                val response = client.post("/announce/struct") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        """
                        {
                            candidate: "flink",
                            version: "1.15.2",
                            hashtag: "",
                            url: "https://archive.apache.org/dist/flink/flink-1.15.2/flink-1.15.2-bin-scala_2.12.tgz"
                        }
                        """
                    )
                }

                response shouldHaveStatus HttpStatusCode.OK
            }
        }
    }
})
