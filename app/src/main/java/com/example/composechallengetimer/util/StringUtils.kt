/*Got logic from vsay01 */
package com.example.composechallengetimer.util

import java.util.concurrent.TimeUnit

const val UNSET_TIME = "00h 00m 00s"

//00h 00m 00s
fun String.getSecond() = substring(8, 10)
fun String.getMinute() = substring(4, 6)
fun String.getHour() = substring(0, 2)

fun String.getFirstDigitOfSecond() = substring(startIndex = 8, endIndex = 9)
fun String.getSecondDigitOfSecond() = substring(startIndex = 9, endIndex = 10)
fun String.getFirstDigitOfMinute() = substring(startIndex = 4, endIndex = 5)
fun String.getSecondDigitOfMinute() = substring(startIndex = 5, endIndex = 6)
fun String.getFirstDigitOfHour() = substring(startIndex = 0, endIndex = 1)
fun String.getSecondDigitOfHour() = substring(startIndex = 1, endIndex = 2)

fun String.replaceFirstDigitOfSecond(number: String) = replaceRange(startIndex = 8, endIndex = 9, number)
fun String.replaceSecondDigitOfSecond(number: String) = replaceRange(startIndex = 9, endIndex = 10, number)
fun String.replaceFirstDigitOfMinute(number: String) = replaceRange(startIndex = 4, endIndex = 5, number)
fun String.replaceSecondDigitOfMinute(number: String) = replaceRange(startIndex = 5, endIndex = 6, number)
fun String.replaceFirstDigitOfHour(number: String) = replaceRange(startIndex = 0, endIndex = 1, number)
fun String.replaceSecondDigitOfHour(number: String) = replaceRange(startIndex = 1, endIndex = 2, number)

fun getFormattedTimer(timeMilliseconds: Long): String {
    val seconds = (timeMilliseconds / 1000) % 60
    val minutes = ((timeMilliseconds / 1000) / 60) % 60
    val hours = ((timeMilliseconds / 1000) / 60) / 60
    if (seconds <= 0L && minutes <= 0L && hours <= 0L) {
        return "0"
    }
    return when {
        hours == 0L && minutes == 0L -> "%02ds".format(seconds)
        hours == 0L -> "%02dm %02ds".format(minutes, seconds)
        else -> "%02dh %02dm %02ds".format(hours, minutes, seconds)
    }
}

fun getMillisecondsFromTimer(time: String) = TimeUnit.SECONDS.toMillis(time.getSecond().toLong()) +
        TimeUnit.MINUTES.toMillis(time.getMinute().toLong()) +
        TimeUnit.HOURS.toMillis(time.getHour().toLong())