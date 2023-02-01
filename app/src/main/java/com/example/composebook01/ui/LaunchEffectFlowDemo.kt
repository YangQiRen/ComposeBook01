package com.example.composebook01.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LaunchEffectFlowDemo(
    viewModel: MainViewModel,
) {
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect { event ->
            when(event) {
                is MainViewModel.ScreenEvents.ShowSnackBar -> {
                    println("show snack bar")
                }
                is MainViewModel.ScreenEvents.Navigate -> {
                    println("show snack bar")
                }
            }
        }
    }
}