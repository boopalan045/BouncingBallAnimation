package com.asustug.bouncingballanimation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asustug.bouncingballanimation.ui.theme.BouncingBallAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BouncingBallAnimationTheme {
                TryBouncingBallAnimation(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TryBouncingBallAnimation(modifier: Modifier) {
    var ballPosition by remember { mutableStateOf(Offset(0f, 0f)) }
    val bounceAnimation = rememberInfiniteTransition()
    val yAnimation by bounceAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse)
    )
    ballPosition = Offset(ballPosition.x,yAnimation)
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Bouncing Ball - Animation") }
        )
    })
    {
        Box(
            modifier = modifier.fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(100.dp)
                .background(Color.White)) {
                drawCircle(
                    color = Color.Blue,
                    radius = 50f,
                    center = ballPosition
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BouncingBallAnimationTheme {
        TryBouncingBallAnimation(modifier = Modifier)
    }
}