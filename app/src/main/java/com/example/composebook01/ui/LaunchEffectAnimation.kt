package com.example.composebook01.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun LaunchEffectAnimation(
    counter: Int
) {
    val animatable = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = counter) {
        animatable.animateTo(counter.toFloat())
    }
}