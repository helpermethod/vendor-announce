package io.sdkman.twitter

import io.github.redouane59.twitter.TwitterClient as Twittered
import io.github.redouane59.twitter.signature.TwitterCredentials as TwitteredCredentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TwitterClient(
    apiKey: ApiKey,
    apiSecretKey: ApiSecretKey,
    accessToken: AccessToken,
    accessTokenSecret: AccessTokenSecret,
) {
    private val twittered =
        Twittered(
            TwitteredCredentials(
                apiKey.value,
                apiSecretKey.value,
                accessToken.value,
                accessTokenSecret.value,
                null,
            ),
        )

    suspend fun postTweet(text: String): Unit =
        withContext(Dispatchers.IO) {
            twittered.postTweet(text)
        }
}

@JvmInline
value class ApiKey(val value: String)

@JvmInline
value class ApiSecretKey(val value: String)

@JvmInline
value class AccessToken(val value: String)

@JvmInline
value class AccessTokenSecret(val value: String)
