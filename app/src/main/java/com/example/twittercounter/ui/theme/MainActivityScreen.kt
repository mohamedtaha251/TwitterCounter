package com.example.twittercounter.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twittercounter.R
import com.example.twittercounter.utils.postTweet
import kotlinx.coroutines.launch

@Composable
fun TwitterCharacterCount() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val maxChars = 280

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Twitter character count",
            fontSize = 18.sp,
            color = Color(0xFF1C211F),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Image(
            modifier = Modifier.padding(top = 16.dp),
            painter = painterResource(id = R.drawable.vector),
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(0.5f)) {
                CharacterCounterBox(
                    title = "Characters Typed",
                    count = text.text.length,
                    maxChars = maxChars
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(modifier = Modifier.weight(0.5f)) {
                CharacterCounterBox(
                    title = "Characters Remaining",
                    count = maxChars - text.text.length,
                    maxChars = maxChars
                )
            }
        }

        // TextField for typing
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(color = Color.LightGray, width = 1.dp, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
                    Text(
                        text = "Start typing! You can enter up to 280 characters",
                        color = Color.Gray
                    )
                }
                innerTextField()
            }
        )


        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* Copy Text action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A970)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Copy Text")
            }
            Button(
                onClick = { text = TextFieldValue("") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC0125)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Clear Text")
            }
        }

        Button(
            onClick = {
                scope.launch {
                    postTweet(text.text)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4)),
            shape = RoundedCornerShape(16.dp)

        ) {
            Text(text = "Post tweet", color = Color.White)
        }
    }
}

@Composable
fun CharacterCounterBox(title: String, count: Int, maxChars: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                shape = RoundedCornerShape(12.dp),
                color = colorResource(id = R.color.sky),
                width = 2.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.sky),
                    shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
                )
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp),
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier.padding(22.dp),
            text = "$count/$maxChars",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTwitterCharacterCount() {
    TwitterCharacterCount()
}
