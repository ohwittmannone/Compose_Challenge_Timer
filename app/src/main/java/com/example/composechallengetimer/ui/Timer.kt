package com.example.composechallengetimer.ui

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composechallengetimer.CustomCountdownTimer
import com.example.composechallengetimer.data.TimerViewModel
import com.example.composechallengetimer.util.UNSET_TIME
import com.example.composechallengetimer.util.getMillisecondsFromTimer

private var playButtonVisibility = mutableStateOf(false)
private val timerWithPadVisibility = mutableStateOf(true)

@ExperimentalAnimationApi
@Composable
fun Timer(timerViewModel: TimerViewModel, customTimer: CustomCountdownTimer) {
    Scaffold(
        topBar = { TopBar() }
    ) {
        if (timerWithPadVisibility.value) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Column(Modifier.weight(4f)) {
                    TimeDisplay(timerViewModel)
                    Divider(thickness = 3.dp)
                    NumberPad(timerViewModel)
                }
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlayButton(timerViewModel, customTimer)
                }
            }
        }
        if (!timerWithPadVisibility.value) {
            Countdown(timerViewModel, timerWithPadVisibility, playButtonVisibility)
        }
    }
}

@Composable
private fun TopBar() {
    TopAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Timer", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun TimeDisplay(timerViewModel: TimerViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
    ) {
        val (text, button) = createRefs()
        Text(
            text = timerViewModel.time.value,
            fontSize = 40.sp,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        IconButton(
            onClick = {
                timerViewModel.removeDigit()
                playButtonVisibility.value = timerViewModel.time.value != UNSET_TIME
            },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }) {
            Icon(imageVector = Icons.Filled.Backspace, contentDescription = null)
        }
    }
}

@Composable
private fun NumberPad(timerViewModel: TimerViewModel) {
    Row(modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)) {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(1)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("1", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(2)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("2", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(3)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("3", fontSize = 30.sp)
        }
    }
    Row(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(4)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("4", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(5)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("5", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(6)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("6", fontSize = 30.sp)
        }
    }
    Row(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(7)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("7", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(8)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("8", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(9)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("9", fontSize = 30.sp)
        }
    }
    Row(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                timerViewModel.addDigit(0)
                playButtonVisibility.value = !timerViewModel.time.equals(UNSET_TIME)
            }) {
            Text("0", fontSize = 30.sp)
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun PlayButton(timerViewModel: TimerViewModel, customTimer: CustomCountdownTimer) {
    AnimatedVisibility(
        visible = playButtonVisibility.value,
        enter = slideInVertically(initialOffsetY = { 30 }) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically(targetOffsetY = { 30 }) + fadeOut()
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = null,
            modifier = Modifier
                .background(color = MaterialTheme.colors.secondary, shape = CircleShape)
                .clip(CircleShape)
                .clickable {
                    timerViewModel.totalTime.value = getMillisecondsFromTimer(timerViewModel.time.value)
                    customTimer.start()
                    timerWithPadVisibility.value = false
                    timerViewModel.isTimerRunning.value = true
                }
                .padding(12.dp)
        )
    }
}