package com.example.composechallengetimer.ui

import android.os.CountDownTimer
import com.example.composechallengetimer.data.TimerViewModel
import com.example.composechallengetimer.util.UNSET_TIME
import com.example.composechallengetimer.util.getFormattedTimer

class CustomCountdownTimer(
    millisInFuture: Long,
    countDownInterval: Long = 1000,
    private val timerViewModel: TimerViewModel
) : CountDownTimer(millisInFuture, countDownInterval) {

    override fun onTick(millisUntilFinished: Long) {
        timerViewModel.time.value = getFormattedTimer(millisUntilFinished)
    }

    override fun onFinish() {
        timerViewModel.time.value = UNSET_TIME
    }
}