package io.sdkman.twitter

import io.github.redouane59.twitter.TwitterClient
import io.github.redouane59.twitter.signature.TwitterCredentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TwitterClient(
    apiKey: String,
    apiSecretKey: String,
    accessToken: String,
    accessTokenSecret: String,
) {
    private val twitterClient =
        TwitterClient(
            TwitterCredentials(
                apiKey,
                apiSecretKey,
                accessToken,
                accessTokenSecret,
                null,
            ),
        )

    suspend fun postTweet(text: String) =
        withContext(Dispatchers.IO) {
            twitterClient.postTweet(text)
        }
}
