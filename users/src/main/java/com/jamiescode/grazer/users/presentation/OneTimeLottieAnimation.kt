package com.jamiescode.grazer.users.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun oneTimeLottieAnimation(
    resource: LottieCompositionSpec.RawRes,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    onFinish: () -> Unit,
) {
    val composition by rememberLottieComposition(resource)
    var isPlaying by remember { mutableStateOf(true) }
    val progress by animateLottieCompositionAsState(composition, isPlaying)

    if (progress == 1f) {
        onFinish()
        isPlaying = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        LottieAnimation(
            composition= composition,
            progress = { progress }
        )
    }
}
