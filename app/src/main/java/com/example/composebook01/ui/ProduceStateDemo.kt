package com.example.composebook01.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

@Composable
fun ProduceStateDemo(countUpTo: Int): State<Int> {
    return produceState(initialValue = 0, producer = {
        while (value < countUpTo) {
            delay(1000L)
            value++
        }
    })
}