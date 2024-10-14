package com.jamiescode.grazer.users.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamiescode.grazer.theme.grazerTheme

@Composable
fun userCardButtons() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
    ) {
        val buttonSize = 76.dp
        val iconSize = 64.dp
        OutlinedButton(
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(buttonSize),
            onClick = {},
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "Like",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(iconSize),
            )
        }
        OutlinedButton(
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(buttonSize),
            onClick = {},
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Like",
                tint = Color.Red,
                modifier = Modifier.size(iconSize),
            )
        }
    }
}

@Preview
@Composable
fun userCardButtonsPreview() {
    grazerTheme {
        Column {
            userCardButtons()
        }
    }
}
