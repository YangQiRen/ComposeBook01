package com.example.composebook01.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<ScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _sharedFlow.emit(ScreenEvents.ShowSnackBar("Hello world"))
        }
    }

    sealed class ScreenEvents {
        data class ShowSnackBar(val message: String) : ScreenEvents()
        data class Navigate(val route: String) : ScreenEvents()
    }

//    private val _color = MutableStateFlow(0xFFFFFFFF)
    val color = savedStateHandle.getStateFlow("color", initialValue = 0xFFFFFFFF)

    var composeColor by mutableStateOf(
        savedStateHandle["color"] ?:0xFFFFFFFF
    )
        private set

    fun generateNewColor() {
        val color = Random.nextLong(0xFFFFFFFF)
        savedStateHandle["color"] = color
        composeColor = color
    }
}