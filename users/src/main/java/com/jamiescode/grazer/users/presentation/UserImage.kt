package com.jamiescode.grazer.users.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun userImage(imageUrl: String) {
    val imageSize = 96.dp
    GlideImage(
        model = imageUrl,
        contentDescription = "User image",
        modifier = Modifier.size(imageSize),
        loading = placeholder { userImageLoading(imageSize) },
        failure = placeholder { userImageError(imageSize) },
    )
}

@Composable
private fun userImageLoading(size: Dp) {
    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(modifier = Modifier.size(size))
    }
}

@Composable
private fun userImageError(size: Dp) {
    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "Default user image",
            tint = Color.Red,
            modifier = Modifier.size(size),
        )
    }
}
