package com.example.composebook01

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.math.MathUtils
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composebook01.ui.*
import com.example.composebook01.ui.components.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Surface(
////                color = Color(0xFF101010),
//                modifier = Modifier.fillMaxSize()
//            ) {
////                Navigation()
////                MultiLayerGraphScreenDemo()
//                PermissionDemo()
//            }
//            val viewModel by viewModels<MainViewModel>()
//            val flowColor by viewModel.color.collectAsState()
//            val composeColor = viewModel.composeColor
//            Column(
//                modifier = Modifier.clickable {
//                    viewModel.generateNewColor()
//                }
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                        .background(Color(flowColor))
//                )
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                        .background(Color(composeColor))
//                )
//            }

//            MarqueeList(
//                items = listOf(
//                    "跑馬燈3 稍微長稍微長 非常非常長 非常非常長 非常非常長 非常非常長 非常非常長 非常非常長",
//                    "跑馬燈1",
//                    "跑馬燈2 稍微長稍微長",
//                ),
//                modifier = Modifier.fillMaxWidth().height(100.dp),
//                scrollDuration = 3000,
//                tickerDelay = 16,
//            )

//            TestHorizontalPagerAnimationRow()
            Column {
                CenterTitleBar(title = "短標題")
            }

            /**
            OverlappingRow(
            overlapFactor = 0.7f
            ) {
            val images = intArrayOf(
            R.drawable.logo,
            R.drawable.music_knob,
            R.drawable.parkanpin,
            R.drawable.ic_moonbg,
            R.drawable.ic_midbg,
            R.drawable.ic_outerbg
            )
            for (i in images.indices) {
            Image(
            painter = painterResource(id = images[i]),
            contentDescription = null,
            modifier = Modifier
            .width(64.dp)
            .height(64.dp)
            .border(width = 1.dp, color = Color.White, shape = CircleShape)
            .clip(CircleShape),
            contentScale = ContentScale.Crop

            )
            }
            Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
            .width(64.dp)
            .height(64.dp)
            .border(width = 1.dp, color = Color.Black, shape = CircleShape)
            .clip(CircleShape)
            .background(Color.White),

            ) {
            Text(
            text = "10+",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,

            )
            }
            }
             **/

        }
    }
}

@Composable
fun OverlappingRow(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.1, to = 1.0) overlapFactor: Float = 0.5f,
    content: @Composable () -> Unit,
) {
    val measurePolicy = overlappingRowMeasurePolicy(overlapFactor)
    Layout(
        measurePolicy = measurePolicy,
        content = content,
        modifier = modifier
    )
}

fun overlappingRowMeasurePolicy(overlapFactor: Float) = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map { measurable -> measurable.measure(constraints) }
    val height = placeables.maxOf { it.height }
    val width = (placeables.subList(1, placeables.size)
        .sumOf { it.width } * overlapFactor + placeables[0].width).toInt()
    layout(width, height) {
        var xPos = 0
        for (placeable in placeables) {
            placeable.placeRelative(xPos, 0, 0f)
            xPos += (placeable.width * overlapFactor).toInt()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TestHorizontalPagerAnimationRow() {
    var pagerIndex by remember {
        mutableStateOf(0)
    }
    var currPage by remember {
        mutableStateOf(0)
    }
    var currPageOffset by remember {
        mutableStateOf(0f)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val contentPadding = remember { maxWidth * .141f }

        HorizontalPager(
            count = 10, // 無限循環輪播
            state = rememberPagerState(),
            contentPadding = PaddingValues(horizontal = contentPadding),
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(600.dp)
                .background(Color.LightGray.copy(alpha = .7f)),
        ) { index ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        val offset = calculateCurrentOffsetForPage(index)
                        if (index == 0) {
                            println("offset=$offset")
                        }
                        if (offset > 0) {
                            transformOrigin = TransformOrigin(0f, 0.5f)
                            translationX = size.width * offset
                            val scale = MathUtils.lerp(
                                0.85f,
                                1f,
                                1f - offset.absoluteValue.coerceIn(0f, 1f)
                            )
                            scaleX = scale
                            scaleY = scale
                            alpha = scale
                        }
                    }
                    .aspectRatio(250 / 350f)
                    .background(Color.Green),
            ) {
                Text(
                    text = index.toString(),
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        val debugText = "pagerIndex=$pagerIndex\n" +
                "currPage=$currPage\n" +
                "currPageOffset=$currPageOffset\n"
        Text(
            text = debugText,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
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

