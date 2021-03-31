package com.example.composechallengetimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.composechallengetimer.ui.Timer
import com.example.composechallengetimer.ui.theme.ComposeChallengeTimerTheme


class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val timerViewModel by viewModels<TimerViewModel>()
        val customTimer = CustomCountdownTimer(
            millisInFuture = timerViewModel.totalTime.value,
            timerViewModel = timerViewModel
        )
        setContent {
            ComposeChallengeTimerTheme {
                Timer(timerViewModel, customTimer)
            }
        }
    }
}