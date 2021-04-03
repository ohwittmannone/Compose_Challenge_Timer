package com.example.composechallengetimer.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composechallengetimer.data.TimerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.math.max

private const val ONE_SECOND = 1000

@Composable
fun Countdown(
    model: TimerViewModel,
) {
    val percentageDone =
        max(model.timeRemaining.value.toFloat() - ONE_SECOND, 0f) / model.totalTime.value
    val progress by animateFloatAsState(
        targetValue = percentageDone,
        animationSpec = tween(durationMillis = ONE_SECOND, easing = LinearEasing)
    )

    VisualLogic(model)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        LoadingSpinners(progress)
        CountdownText(model)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp), contentAlignment = Alignment.BottomCenter
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(Modifier.padding(24.dp)) { PlayPauseButton(model) }
                Column(Modifier.padding(24.dp)) { DeleteButton(model) }
            }
            OkButton(model)
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
private fun OkButton(model: TimerViewModel) {
    if (!model.isTimerRunning.value && model.timeRemaining.value == 0L) {
        Button(onClick = {
            model.timerWithPadVisibility.value = true
            model.playButtonVisibility.value = false
        }, shape = RoundedCornerShape(8.dp)) {
            Text(text = "OK", fontSize = 30.sp)
        }
    }
}

@Composable
private fun PlayPauseButton(model: TimerViewModel) {
    if (model.timeRemaining.value != 0L) {
        Icon(
            imageVector = if (model.isTimerRunning.value) Icons.Filled.Pause else Icons.Filled.PlayArrow,
            contentDescription = null,
            modifier = Modifier
                .background(color = MaterialTheme.colors.secondary, shape = CircleShape)
                .clip(CircleShape)
                .clickable { model.isTimerRunning.value = !model.isTimerRunning.value }
                .padding(12.dp)
        )
    }
}

@Composable
private fun DeleteButton(model: TimerViewModel) {
    if (model.timeRemaining.value != 0L) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
            Modifier
                .background(color = MaterialTheme.colors.secondary, shape = CircleShape)
                .clip(CircleShape)
                .clickable { model.reset() }
                .padding(12.dp)
        )
    }
}