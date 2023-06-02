package com.example.composebook01.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MarqueeList(
    items: List<String>,
    modifier: Modifier = Modifier,
    scrollDuration: Int = 3000,
    tickerDelay: Int = 16
) {
    val screenWidthPx =
        with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val coroutineScope = rememberCoroutineScope()
    val animatableOffset = remember { Animatable(initialValue = -screenWidthPx) }
    val currentItemIndex = remember { mutableStateOf(0) }

    LaunchedEffect(currentItemIndex.value) {
        animatableOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(scrollDuration, easing = LinearEasing)
        )
        animatableOffset.snapTo(-screenWidthPx)
        currentItemIndex.value++
        if (currentItemIndex.value >= items.size) {
            currentItemIndex.value = 0
        }
        delay(tickerDelay.toLong())
    }

    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .graphicsLayer {
                translationX = animatableOffset.value
            }
    ) {
        MarqueeText(text = items[currentItemIndex.value])
    }
}

@Composable
fun MarqueeText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.body1
) {
    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        Text(
            text = text,
            style = textStyle,
            overflow = TextOverflow.Visible
        )
    }
}


