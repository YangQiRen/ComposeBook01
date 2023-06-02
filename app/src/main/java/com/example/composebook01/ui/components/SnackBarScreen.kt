package com.example.composebook01.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SnackbarScreen() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var isStyleOne by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
        ) {
            FloatingActionButton(
                onClick = {
                    //Important part here
                    scope.launch {
                        isStyleOne = true
                        snackbarHostState.showSnackbar("Hello style one")
                    }
                    //
                },
                content = { Icon(imageVector = Icons.Default.Add, contentDescription = "") }
            )
            FloatingActionButton(
                onClick = {
                    //Important part here
                    scope.launch {
                        isStyleOne = false
                        snackbarHostState.showSnackbar("Fuck style2")
                    }
                    //
                },
                content = { Icon(imageVector = Icons.Default.Share, contentDescription = "") }
            )
        }
        SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = it.message,
                modifier = Modifier
                    .background(
                        color = if (isStyleOne) Color.Blue else Color.Green,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .padding(8.dp)
            )
        }
    }

}