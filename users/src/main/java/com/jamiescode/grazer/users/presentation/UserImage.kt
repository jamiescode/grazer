package com.jamiescode.grazer.users.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.jamiescode.grazer.theme.grazerTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun userImage(
    imageUrl: String,
    modifier: Modifier,
) {
    GlideImage(
        model = imageUrl,
        contentDescription = "User image",
        modifier = modifier.fillMaxWidth(),
        loading = placeholder { userImageLoading() },
        failure = placeholder { userImageError() },
    )
}

@Composable
private fun userImageLoading() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun userImageError() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "Default user image",
            tint = Color.Red,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun userImagePreview() {
    grazerTheme {
        Column {
            userImage(
                imageUrl = "https://thispersondoesnotexist.com/",
                modifier = Modifier,
            )
        }
    }
}

@Preview
@Composable
fun userImageLoadingPreview() {
    grazerTheme {
        Column {
            userImageLoading()
        }
    }
}

@Preview
@Composable
fun userImageErrorPreview() {
    grazerTheme {
        Column {
            userImageError()
        }
    }
}
