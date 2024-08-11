package com.example.twittercounter.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TwitterCharacterCount() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val maxChars = 280

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Twitter character count",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Twitter Logo (you would need to add an Image composable here)

        // Character Counters
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CharacterCounterBox(
                title = "Characters Typed",
                count = text.text.length,
                maxChars = maxChars
            )
            CharacterCounterBox(
                title = "Characters Remaining",
                count = maxChars - text.text.length,
                maxChars = maxChars
            )
        }

        // TextField for typing
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.LightGray)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
                    Text(text = "Start typing! You can enter up to 280 characters")
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* Copy Text action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text(text = "Copy Text")
            }
            Button(
                onClick = { text = TextFieldValue("") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(text = "Clear Text")
            }
        }

        // Post tweet button
        Button(
            onClick = { /* Post Tweet action */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Post tweet", color = Color.White)
        }
    }
}

@Composable
fun CharacterCounterBox(title: String, count: Int, maxChars: Int) {
    Column(
        modifier = Modifier
           // .weight(1f)
            .background(Color(0xFFE7F3FF))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontWeight = FontWeight.Bold)
        Text(
            text = "$count/$maxChars",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTwitterCharacterCount() {
    TwitterCharacterCount()
}
