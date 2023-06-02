package com.example.composebook01.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CenterTitleBar(
    title: String,
) {
    println("CenterTitleBar title=$title")
    Layout(
        modifier = Modifier.background(Color.Green.copy(.3f)),
        content = {
            Icon(
                painter = painterResource(id = com.google.android.material.R.drawable.ic_arrow_back_black_24),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.Red.copy(.3f))
                    .padding(6.dp)
                    .background(Color.Blue.copy(.3f))
            )

            Text(
                text = title,
                color = Color.Black,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .background(Color.Yellow.copy(.5f))
                    .padding(horizontal = 8.dp, vertical = 12.dp),
            )

        }
    ) { measurables, constraints ->
        val backIcon = measurables[0].measure(constraints)
        val textTitle = measurables[1].measure(
            constraints.copy(
                maxWidth = constraints.maxWidth - backIcon.width
            )
        )
        val maxHeight = maxOf(backIcon.height, textTitle.height)

        val titleLeft = if (textTitle.width + backIcon.width < constraints.maxWidth) {
            // 標題置中
            (constraints.maxWidth - textTitle.width) / 2
        } else {
            backIcon.width
        }

        println("CenterTitleBar constraints width=${constraints.maxWidth}, height=${constraints.maxHeight}")
        println("CenterTitleBar backIcon width=${backIcon.width}, height=${backIcon.height}, measureWidth=${backIcon.measuredWidth}, measureHeight=${backIcon.measuredHeight}")
        println("CenterTitleBar textTitle width=${textTitle.width}, height=${textTitle.height}, measureWidth=${textTitle.measuredWidth}, measureHeight=${textTitle.measuredHeight}")

        layout(
            width = constraints.maxWidth,
            height = maxHeight,
        ) {
            // 根據計算得出的位置放置元件
            backIcon.placeRelative(
                x = 0,
                y = (maxHeight - backIcon.height) / 2
            )
            textTitle.placeRelative(
                x = titleLeft,
                y = (maxHeight - textTitle.height) / 2
            )
        }
    }

}