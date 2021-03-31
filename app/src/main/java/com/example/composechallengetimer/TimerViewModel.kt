package com.example.composechallengetimer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composechallengetimer.util.*

class TimerViewModel : ViewModel() {
    val time = mutableStateOf("00h 00m 00s")
    val totalTime = mutableStateOf(0L)
    val timeRemaining = mutableStateOf(0L)
    val isTimerRunning = mutableStateOf(false)

    init {
        time.value = "00h 00m 00s"
    }

    fun getFormattedTimer(timeMilliseconds: Long): String {
        val seconds = (timeMilliseconds / 1000) % 60
        val minutes = ((timeMilliseconds / 1000) / 60) % 60
        val hours = ((timeMilliseconds / 1000) / 60) / 60
        if (seconds <= 0L && minutes <= 0L && hours <= 0L) {
            return "0"
        }
        return when {
            hours == 0L && minutes == 0L -> "%02ds".format(seconds)
            hours == 0L -> "%02dm:%02ds".format(minutes, seconds)
            else -> "%02dh:%02dm:%02ds".format(hours, minutes, seconds)
        }
    }

    fun addDigit(number: Int) {
        when {
            time.value.getFirstDigitOfHour() == "0" -> {
                time.value = time.value.replaceFirstDigitOfHour(time.value.getSecondDigitOfHour())
                time.value = time.value.replaceSecondDigitOfHour(time.value.getFirstDigitOfMinute())
                time.value = time.value.replaceFirstDigitOfMinute(time.value.getSecondDigitOfMinute())
                time.value = time.value.replaceSecondDigitOfMinute(time.value.getFirstDigitOfSecond())
                time.value = time.value.replaceFirstDigitOfSecond(time.value.getSecondDigitOfSecond())
                time.value = time.value.replaceSecondDigitOfSecond(number.toString())
            }
            time.value.getSecondDigitOfHour() == "0" -> {
                time.value = time.value.replaceSecondDigitOfHour(time.value.getFirstDigitOfMinute())
                time.value = time.value.replaceFirstDigitOfMinute(time.value.getSecondDigitOfMinute())
                time.value = time.value.replaceSecondDigitOfMinute(time.value.getFirstDigitOfSecond())
                time.value = time.value.replaceFirstDigitOfSecond(time.value.getSecondDigitOfSecond())
                time.value = time.value.replaceSecondDigitOfSecond(number.toString())
            }
            time.value.getFirstDigitOfMinute() == "0" -> {
                time.value = time.value.replaceFirstDigitOfMinute(time.value.getSecondDigitOfMinute())
                time.value = time.value.replaceSecondDigitOfMinute(time.value.getFirstDigitOfSecond())
                time.value = time.value.replaceFirstDigitOfSecond(time.value.getSecondDigitOfSecond())
                time.value = time.value.replaceSecondDigitOfSecond(number.toString())
            }
            time.value.getSecondDigitOfMinute() == "0" -> {
                time.value = time.value.replaceSecondDigitOfMinute(time.value.getFirstDigitOfSecond())
                time.value = time.value.replaceFirstDigitOfSecond(time.value.getSecondDigitOfSecond())
                time.value = time.value.replaceSecondDigitOfSecond(number.toString())
            }
            time.value.getFirstDigitOfSecond() == "0" -> {
                time.value = time.value.replaceFirstDigitOfSecond(time.value.getSecondDigitOfSecond())
                time.value = time.value.replaceSecondDigitOfSecond(number.toString())
            }
            time.value.getSecondDigitOfSecond() == "0" -> {
                time.value = time.value.replaceSecondDigitOfSecond(number.toString())
            }
        }
        totalTime.value = getMillisecondsFromTimer(time.value)
        timeRemaining.value = getMillisecondsFromTimer(time.value)
    }

    fun removeDigit() {
        when {
            time.value.getFirstDigitOfHour() != "0" -> {
                time.value = time.value.replaceSecondDigitOfSecond(time.value.getFirstDigitOfSecond())
                time.value = time.value.replaceFirstDigitOfSecond(time.value.getSecondDigitOfMinute())
                time.value = time.value.replaceSecondDigitOfMinute(time.value.getFirstDigitOfMinute())
                time.value = time.value.replaceFirstDigitOfMinute(time.value.getSecondDigitOfHour())
                time.value = time.value.replaceSecondDigitOfHour(time.value.getFirstDigitOfHour())
                time.value = time.value.replaceFirstDigitOfHour("0")
            }
            time.value.getSecondDigitOfHour() != "0" -> {
                time.value = time.value.replaceSecondDigitOfSecond(time.value.getFirstDigitOfSecond())
                time.value = time.value.replaceFirstDigitOfSecond(time.value.getSecondDigitOfMinute())
                time.value = time.value.replaceSecondDigitOfMinute(time.value.getFirstDigitOfMinute())
                time.value = time.value.replaceFirstDigitOfMinute(time.value.getSecondDigitOfHour())
                time.value = time.value.replaceSecondDigitOfHour("0")
                time.value = time.value.replaceFirstDigitOfHour("0")
            }
            time.value.getFirstDigitOfMinute() != "0" -> {
                time.value = time.value.replaceSecondDigitOfSecond(time.value.getFirstDigitOfSecond())
                time.value = time.value.replaceFirstDigitOfSecond(time.value.getSecondDigitOfMinute())
                time.value = time.value.replaceSecondDigitOfMinute(time.value.getFirstDigitOfMinute())
                time.value = time.value.replaceFirstDigitOfMinute("0")
                time.value = time.value.replaceSecondDigitOfHour("0")
                time.value = time.value.replaceFirstDigitOfHour("0")
            }
            time.value.getSecondDigitOfMinute() != "0" -> {
                time.value = time.value.replaceSecondDigitOfSecond(time.value.getFirstDigitOfSecond())
                time.value = time.value.replaceFirstDigitOfSecond(time.value.getSecondDigitOfMinute())
                time.value = time.value.replaceSecondDigitOfMinute("0")
                time.value = time.value.replaceFirstDigitOfMinute("0")
                time.value = time.value.replaceSecondDigitOfHour("0")
                time.value = time.value.replaceFirstDigitOfHour("0")
            }
            time.value.getFirstDigitOfSecond() != "0" -> {
                time.value = time.value.replaceSecondDigitOfSecond(time.value.getFirstDigitOfSecond())
                time.value = time.value.replaceFirstDigitOfSecond("0")
                time.value = time.value.replaceSecondDigitOfMinute("0")
                time.value = time.value.replaceFirstDigitOfMinute("0")
                time.value = time.value.replaceSecondDigitOfHour("0")
                time.value = time.value.replaceFirstDigitOfHour("0")
            }
            time.value.getSecondDigitOfSecond() != "0" -> {
                time.value = time.value.replaceSecondDigitOfSecond("0")
                time.value = time.value.replaceFirstDigitOfSecond("0")
                time.value = time.value.replaceSecondDigitOfMinute("0")
                time.value = time.value.replaceFirstDigitOfMinute("0")
                time.value = time.value.replaceSecondDigitOfHour("0")
                time.value = time.value.replaceFirstDigitOfHour("0")
            }
        }
        totalTime.value = getMillisecondsFromTimer(time.value)
        timeRemaining.value = getMillisecondsFromTimer(time.value)
    }
}