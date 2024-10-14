package com.jamiescode.grazer.users.presentation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class UserAttributeIcon(
    open val contentDescription: String,
) {
    data class DrawableIcon(
        @DrawableRes val icon: Int,
        override val contentDescription: String,
    ) : UserAttributeIcon(contentDescription)

    data class VectorIcon(
        val icon: ImageVector,
        override val contentDescription: String,
    ) : UserAttributeIcon(contentDescription)
}
