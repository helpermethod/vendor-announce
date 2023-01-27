package io.sdkman.routes

import kotlinx.serialization.Serializable

@Serializable
data class StructuredAnnounce(
    val candidate: String,
    val version: String,
    val hashtag: String,
    val url: String,
)
