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

fun countTwitterCharacters(text: String): Int {
    var count = 0
    var index = 0

    while (index < text.length) {
        val codePoint = text.codePointAt(index)
        val charCount = Character.charCount(codePoint)

        val isSpecialCharacter = codePoint in 0x1F600..0x1F64F || // Emoticons
                codePoint in 0x1F300..0x1F5FF || // Misc symbols and pictographs
                codePoint in 0x1F680..0x1F6FF || // Transport and map symbols
                codePoint in 0x1F700..0x1F77F || // Alchemical symbols
                codePoint in 0x1F780..0x1F7FF || // Geometric shapes
                codePoint in 0x1F800..0x1F8FF || // Supplemental Arrows-C
                codePoint in 0x1F900..0x1F9FF || // Supplemental Symbols and Pictographs
                codePoint in 0x1FA00..0x1FA6F || // Chess symbols
                codePoint in 0x1FA70..0x1FAFF || // Symbols and Pictographs Extended-A
                codePoint in 0x2600..0x26FF ||   // Misc symbols
                codePoint in 0x2700..0x27BF ||   // Dingbats
                codePoint in 0x2300..0x23FF     // Misc technical
        val additionalCount = if (isSpecialCharacter) 2 else 1

        count += additionalCount
        index += charCount
    }

    return count
}
