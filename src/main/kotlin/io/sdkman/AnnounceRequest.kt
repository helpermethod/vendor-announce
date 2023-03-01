package io.sdkman

import kotlinx.serialization.Serializable

@Serializable
data class AnnounceRequest(
    val candidate: String,
    val version: String,
    val url: String?,
)
