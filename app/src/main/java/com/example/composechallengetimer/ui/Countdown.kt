package com.example.composechallengetimer.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composechallengetimer.data.TimerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.math.max

private const val ONE_SECOND = 1000

@Composable
fun Countdown(
    timerViewModel: TimerViewModel,
    timerWithPadVisibility: MutableState<Boolean>,
    playButtonVisibility: MutableState<Boolean>
) {
    val percentageDone =
        max(timerViewModel.timeRemaining.value.toFloat() - ONE_SECOND, 0f) / timerViewModel.totalTime.value
    val progress by animateFloatAsState(
        targetValue = percentageDone,
        animationSpec = tween(durationMillis = ONE_SECOND, easing = LinearEasing)
    )

    VisualLogic(timerViewModel)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        LoadingSpinners(progress)
        CountdownText(timerViewModel)
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp), contentAlignment = Alignment.BottomCenter) {
            OkButton(timerViewModel, timerWithPadVisibility, playButtonVisibility)
        }
    }
}

@Composable
fun VisualLogic(model: TimerViewModel) {
    if (model.isTimerRunning.value) {
        LaunchedEffect("Timer") {
            while (isActive) {
                delay(ONE_SECOND.toLong())
                if (isActive) {
                    if (model.isTimerRunning.value) {
                        model.timeRemaining.value = model.timeRemaining.value - ONE_SECOND
                    }
                    if (model.timeRemaining.value == 0L) {
                        model.totalTime.value = 0
                        model.isTimerRunning.value = false
                    }
                }
            }
        }
    }
}

@Composable
private fun CountdownText(model: TimerViewModel) {
    Box(Modifier.aspectRatio(1.85f), contentAlignment = Alignment.BottomCenter) {
        Text(
            text = if (model.timeRemaining.value != 0L) {
                model.getFormattedTimer(model.timeRemaining.value)
            } else {
                "Time is up!"
            },
            fontSize = 20.sp
        )
    }
}

@Composable
private fun LoadingSpinners(progress: Float) {
    CircularProgressIndicator(
        progress = 1F,
        color = MaterialTheme.colors.secondary,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        strokeWidth = 8.dp
    )
    if (progress >= 0) {
        CircularProgressIndicator(
            //this goes behind the first circle
            progress = progress,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            color = MaterialTheme.colors.onSecondary,
            strokeWidth = 9.dp,
        )
    }
}

@Composable
private fun OkButton(
    timerViewModel: TimerViewModel,
    timerWithPadVisibility: MutableState<Boolean>,
    playButtonVisibility: MutableState<Boolean>
) {
    if (!timerViewModel.isTimerRunning.value) {
        Button(onClick = {
            timerWithPadVisibility.value = true
            playButtonVisibility.value = false
        }, shape = RoundedCornerShape(8.dp)) {
            Text(text = "OK", fontSize = 30.sp)
        }
    }
}