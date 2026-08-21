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
import com.example.unscramble.ui.theme.UnscrambleTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscramble.GameViewModel

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
fun GameScreen() {

    val viewModel: GameViewModel = viewModel()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    )
    {

        Text(text = "UNSCRAMBLE", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(32.dp))

    Text(
        text = viewModel.words[viewModel.currentWordIndex],
        fontSize = 40.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Unscramble the word!")
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.userAnswer,
            onValueChange = { viewModel.userAnswer = it },
            label = { Text("Enter your answer") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {})


            {
            Text("SUBMIT")}
        }
        Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = "Score: ${viewModel.score}"

    )
}