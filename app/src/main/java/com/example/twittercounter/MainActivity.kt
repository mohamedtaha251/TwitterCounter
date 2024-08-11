package com.example.twittercounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.twittercounter.ui.theme.TwitterCharacterCount
import com.example.twittercounter.ui.theme.TwitterCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TwitterCounterTheme {
                TwitterCharacterCount()
            }
        }
    }
}
