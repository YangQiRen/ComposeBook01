package com.example.composebook01

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composebook01.ui.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
//                color = Color(0xFF101010),
                modifier = Modifier.fillMaxSize()
            ) {
//                Navigation()
                val moonScrollSpeed = 0.08f
                val midBgScrollSpeed = 0.03f

                val imageHeight = (LocalConfiguration.current.screenWidthDp * (2f / 3f)).dp
                val lazyListState = rememberLazyListState()

                var moonOffset by remember {
                    mutableStateOf(0f)
                }
                var midBgOffset by remember {
                    mutableStateOf(0f)
                }
                val nestedScrollConnection = object : NestedScrollConnection {
                    override fun onPreScroll(
                        available: Offset,
                        source: NestedScrollSource
                    ): Offset {
                        val delta = available.y
                        moonOffset += delta * moonScrollSpeed
                        midBgOffset += delta * midBgScrollSpeed
                        return super.onPreScroll(available, source)
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .nestedScroll(nestedScrollConnection),
                    state = lazyListState
                ) {
                    items(10) {
                        Text(
                            text = "Sample1 Item $it",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .clipToBounds()
                                .fillMaxWidth()
                                .height(imageHeight)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color(0xFFf36b21),
                                            Color(0xFFf9a521)
                                        )
                                    )
                                )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_moonbg),
                                contentDescription = "moon",
                                contentScale = ContentScale.FillWidth,
                                alignment = Alignment.BottomCenter,
                                modifier = Modifier.matchParentSize()
                                    .graphicsLayer {
                                        translationY = moonOffset
                                    }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_midbg),
                                contentDescription = "mid bg",
                                contentScale = ContentScale.FillWidth,
                                alignment = Alignment.BottomCenter,
                                modifier = Modifier.matchParentSize()
                                    .graphicsLayer {
                                        translationY = midBgOffset
                                    }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_outerbg),
                                contentDescription = "outer bg",
                                contentScale = ContentScale.FillWidth,
                                alignment = Alignment.BottomCenter,
                                modifier = Modifier.matchParentSize()
                            )
                        }
                    }
                    items(20) {
                        Text(
                            text = "Sample2 Item $it",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Main Screen", color = Color.White)
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            1f,
//            animationSpec = spring(
//                dampingRatio = 0.3f,
//                stiffness = 200f
//            )
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(Screen.MainScreen.route)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}

