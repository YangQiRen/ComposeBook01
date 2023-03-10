package com.example.composebook01.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

@Composable
fun DerivedStateDemo() {
    var counter by remember {
        mutableStateOf(0)
    }
    val counterText by derivedStateOf {
        "The counter is $counter"
    }
//    val counterText  = "The counter is $counter"
    Button(onClick = { counter++ }) {
        Text(text = counterText)
    }
}