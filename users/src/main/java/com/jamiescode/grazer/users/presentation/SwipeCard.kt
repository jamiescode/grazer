package com.jamiescode.grazer.users.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

/**
 * Swipe card composable from:
 * https://medium.com/@lakshyasukhralia/building-a-tinder-like-swipe-card-in-jetpack-compose-facb37f6f28e
 *
 * Adapted for Grazer by Jamie. Added AnimatedVisibility so that the card disappears.
 */
@Suppress("MagicNumber", "LongParameterList")
@Composable
fun swipeCard(
    modifier: Modifier = Modifier,
    onSwipeLeft: () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    swipeThreshold: Float = 400f,
    sensitivityFactor: Float = 3f,
    content: @Composable () -> Unit,
) {
    var offset by remember { mutableStateOf(0f) }
    var dismissRight by remember { mutableStateOf(false) }
    var dismissLeft by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current.density

    LaunchedEffect(dismissRight) {
        if (dismissRight) {
            delay(300)
            visible = false
            onSwipeRight.invoke()
            dismissRight = false
        }
    }

    LaunchedEffect(dismissLeft) {
        if (dismissLeft) {
            delay(300)
            visible = false
            onSwipeLeft.invoke()
            dismissLeft = false
        }
    }

    AnimatedVisibility(visible) {
        if (visible) {
            Box(
                modifier =
                    modifier
                        .offset { IntOffset(offset.roundToInt(), 0) }
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures(onDragEnd = {
                                offset = 0f
                            }) { change, dragAmount ->

                                offset += (dragAmount / density) * sensitivityFactor
                                when {
                                    offset > swipeThreshold -> {
                                        dismissRight = true
                                    }

                                    offset < -swipeThreshold -> {
                                        dismissLeft = true
                                    }
                                }
                                if (change.positionChange() != Offset.Zero) change.consume()
                            }
                        }.graphicsLayer(
                            alpha = 10f - animateFloatAsState(if (dismissRight) 1f else 0f).value,
                            rotationZ = animateFloatAsState(offset / 50).value,
                        ),
            ) {
                content()
            }
        }
    }
}
