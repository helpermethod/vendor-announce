package io.sdkman

import kotlinx.serialization.Serializable

@Serializable
data class AnnounceRequest(
    val candidate: Candidate,
    val version: Version,
    val url: Url?,
)

@Serializable
@JvmInline
value class Candidate(val value: String)

@Serializable
@JvmInline
value class Version(val value: String)

@Serializable
@JvmInline
value class Url(val value: String)
