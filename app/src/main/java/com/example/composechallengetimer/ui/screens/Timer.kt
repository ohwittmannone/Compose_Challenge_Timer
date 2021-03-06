package com.example.composechallengetimer.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composechallengetimer.data.TimerViewModel
import com.example.composechallengetimer.ui.CustomCountdownTimer
import com.example.composechallengetimer.util.UNSET_TIME
import com.example.composechallengetimer.util.getMillisecondsFromTimer

@ExperimentalAnimationApi
@Composable
fun Timer(model: TimerViewModel, customTimer: CustomCountdownTimer) {
    Scaffold(topBar = { TopBar() }) {
        if (model.timerWithPadVisibility.value) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Column(Modifier.weight(4f)) {
                    TimeDisplay(model)
                    Divider(thickness = 3.dp)
                    NumberPad(model)
                }
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlayButton(model, customTimer)
                }
            }
        }
        if (!model.timerWithPadVisibility.value) Countdown(model)
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
private fun TimeDisplay(model: TimerViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
    ) {
        val (text, button) = createRefs()
        Text(
            text = model.time.value,
            fontSize = 40.sp,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        IconButton(
            onClick = {
                model.removeDigit()
                model.playButtonVisibility.value = model.time.value != UNSET_TIME
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
private fun NumberPad(model: TimerViewModel) {
    Row(modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)) {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                model.addDigit(1)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
            }) {
            Text("1", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                model.addDigit(2)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
            }) {
            Text("2", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                model.addDigit(3)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
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
                model.addDigit(4)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
            }) {
            Text("4", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                model.addDigit(5)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
            }) {
            Text("5", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                model.addDigit(6)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
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
                model.addDigit(7)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
            }) {
            Text("7", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                model.addDigit(8)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
            }) {
            Text("8", fontSize = 30.sp)
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                model.addDigit(9)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
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
                model.addDigit(0)
                model.playButtonVisibility.value = !model.time.equals(UNSET_TIME)
            }) {
            Text("0", fontSize = 30.sp)
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun PlayButton(model: TimerViewModel, customTimer: CustomCountdownTimer) {
    AnimatedVisibility(
        visible = model.playButtonVisibility.value,
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
                    model.totalTime.value = getMillisecondsFromTimer(model.time.value)
                    customTimer.start()
                    model.timerWithPadVisibility.value = false
                    model.isTimerRunning.value = true
                }
                .padding(12.dp)
        )
    }
}