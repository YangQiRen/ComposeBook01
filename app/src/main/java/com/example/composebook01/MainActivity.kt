package com.example.composebook01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.composebook01.ui.*
import com.example.composebook01.ui.theme.ComposeBook01Theme

class MainActivity : ComponentActivity() {
//    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            var text by remember {
//                mutableStateOf("")
//            }
            ComposeBook01Theme {
//                LaunchedEffect(key1 = text) {
//                    delay(1000L)
//                    println("The text is $text")
//                }
//                LaunchEffectFlowDemo(viewModel = MainViewModel())
//                Button

//                DerivedStateDemo()

//                ProduceStateDemo(countUpTo = 1)

//                SideEffectDemo(nonComposeCounter = 1)
            }
        }
    }
}
