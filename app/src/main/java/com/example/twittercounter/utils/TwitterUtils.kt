package com.example.twittercounter.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

fun getTwitterInstance(): Twitter {
    val cb = ConfigurationBuilder()
    cb.setDebugEnabled(true)
        .setOAuthConsumerKey("nA0Xk73ea7BdNzoIkNMt8WG9v")
        .setOAuthConsumerSecret("mFJmGSNba9XuMO07uSHvqpfiocq2if1CgzdqnTD7sLpaQKVP19")
        .setOAuthAccessToken("729825744042463232-HxhIFmtqkWpoDcVJ7tU7Jx3FZLEwAff")
        .setOAuthAccessTokenSecret("HFJPp5cOxsuVUvL8GcFx0yfzH4bmnTsaDDIbVoFXLDe46")
    val tf = TwitterFactory(cb.build())
    return tf.instance
}

suspend fun postTweet(tweetText: String) {
    withContext(Dispatchers.IO) {
        try {
            val twitter = getTwitterInstance()
            twitter.updateStatus(tweetText)
            println("Tweet posted successfully!")
        } catch (e: TwitterException) {
            e.printStackTrace()
            println("Failed to post tweet")
        }
    }
}