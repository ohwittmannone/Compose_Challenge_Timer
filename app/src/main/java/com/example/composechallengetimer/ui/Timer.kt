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
import com.example.composechallengetimer.TimerViewModel

private val playVisibility = mutableStateOf(false)

@ExperimentalAnimationApi
@Composable
fun Timer(timerViewModel: TimerViewModel) {
    Scaffold(
        topBar = { TopBar() }
    ) {
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
                PlayButton()
            }
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
                playVisibility.value = timerViewModel.time.value != "00h 00m 00s"
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
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
                playVisibility.value = !timerViewModel.time.equals("00h 00m 00s")
            }) {
            Text("0", fontSize = 30.sp)
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun PlayButton() {
    AnimatedVisibility(
        visible = playVisibility.value,
        enter = slideInVertically(initialOffsetY = { 30 }) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically(targetOffsetY = { 30 }) + fadeOut()
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = null,
            modifier = Modifier
                .background(color = MaterialTheme.colors.secondary, shape = CircleShape)
                .clip(CircleShape)
                .clickable { /*TODO*/ }
                .padding(12.dp)
        )
    }
}