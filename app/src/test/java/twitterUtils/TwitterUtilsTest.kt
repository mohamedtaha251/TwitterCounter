package com.example.twittercounter.utils

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import twitter4j.Status
import twitter4j.Twitter
import twitter4j.TwitterException

@OptIn(ExperimentalCoroutinesApi::class)
class TwitterUtilsTest {

    private lateinit var mockTwitter: Twitter
    private lateinit var mockStatus: Status

    @Before
    fun setup() {
        mockTwitter = mockk()
        mockStatus = mockk()
        mockkStatic("com.example.twittercounter.utils.TwitterUtilsKt")
    }

    @Test
    fun testPostTweet_Success() = runTest {
        // Given
        val tweetText = "Hello Twitter!"
        every { getTwitterInstance() } returns mockTwitter
        every { mockTwitter.updateStatus(tweetText) } returns mockStatus

        // When
        postTweet(tweetText)

        // Then
        verify { mockTwitter.updateStatus(tweetText) }
    }

    @Test
    fun testPostTweet_Failure() = runTest {
        // Given
        val tweetText = "Hello Twitter!"
        every { getTwitterInstance() } returns mockTwitter
        every { mockTwitter.updateStatus(tweetText) } throws TwitterException("Error")

        // When
        postTweet(tweetText)

        // Then
        verify { mockTwitter.updateStatus(tweetText) }
    }
}
