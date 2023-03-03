package io.sdkman

import io.sdkman.twitter.TwitterClient

class AnnounceService(private val twitterClient: TwitterClient) {
    suspend fun announce(candidate: Candidate, version: Version, url: Url?) {
        val status = buildString {
            if (url?.value != null) append("${url.value} ")
            append("${candidate.value} ${version.value} available on SDKMAN!")
        }

        twitterClient.postTweet(status)
    }
}
