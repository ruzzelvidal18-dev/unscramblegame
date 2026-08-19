package com.example.unscramble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    val words = listOf("CAT", "DOG", "BOOK")

    var currentWordIndex by remember { mutableIntStateOf(0) }
    var userAnswer by remember { mutableStateOf("") }
    var score by remember { mutableIntStateOf(0) }

    val correctAnswer = words[currentWordIndex]

    // Scrambles the current word whenever the index changes
    val scrambledWord = remember(currentWordIndex) {
        val chars = correctAnswer.toCharArray()
        while (String(chars) == correctAnswer && correctAnswer.length > 1) {
            chars.shuffle()
        }
        String(chars)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "UNSCRAMBLE", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(32.dp))

        // Display the scrambled word instead of the answer
        Text(text = scrambledWord, fontSize = 40.sp)

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
                if (userAnswer.trim().equals(correctAnswer, ignoreCase = true)) {
                    score++
                    userAnswer = ""
                    if (currentWordIndex < words.size - 1) {
                        currentWordIndex++
                    }
                }
            }
        ) {
            Text("SUBMIT")
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Score: $score", fontSize = 20.sp)
    }
}