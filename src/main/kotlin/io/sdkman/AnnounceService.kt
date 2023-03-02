package io.sdkman

import io.sdkman.twitter.TwitterClient

class AnnounceService(private val twitterClient: TwitterClient) {
    suspend fun announce(announceRequest: AnnounceRequest) {
        val status = buildString {
            announceRequest.url?.let { append("$it ") }
            append("${announceRequest.candidate} ${announceRequest.version} available on SDKMAN!")
        }

        twitterClient.postTweet(status)
    }
}
