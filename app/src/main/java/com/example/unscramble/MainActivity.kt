package com.example.unscramble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnscrambleTheme {
                GameScreen()
            }
        }
    }
}

@Composable
fun UnscrambleTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}

@Composable
fun GameScreen() {
    // 1. Initialized as empty string so the field doesn't start pre-filled
    var userAnswer by remember { mutableStateOf("") }
    val correctAnswer = "CAT"
    var score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "UNSCRAMBLE", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "TAC", fontSize = 40.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Unscramble the word!")
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = userAnswer,
            onValueChange = { userAnswer = it },
            label = { Text("Enter your answer") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                // 2. Ignores spaces and case differences when comparing
                if (userAnswer.trim().equals(correctAnswer, ignoreCase = true)) {
                    score++
                    userAnswer = "" // Clear input field on correct guess
                }
            }
        ) {
            Text("SUBMIT")
        }

        Spacer(modifier = Modifier.height(32.dp))
        // 3. Added '$' to display the actual variable value
        Text(text = "Score: $score", fontSize = 20.sp)
    }
}