package com.example.composechallengetimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.composechallengetimer.ui.Timer
import com.example.composechallengetimer.ui.theme.ComposeChallengeTimerTheme


class MainActivity : ComponentActivity() {
    private val timerViewModel by viewModels<TimerViewModel>()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChallengeTimerTheme {
                Timer(timerViewModel)
            }
        }
    }
}