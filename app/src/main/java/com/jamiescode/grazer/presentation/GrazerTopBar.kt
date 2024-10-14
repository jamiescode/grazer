package com.jamiescode.grazer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamiescode.grazer.R
import com.jamiescode.grazer.theme.grazerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun grazerTopBar() {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
                    style =
                        MaterialTheme.typography.displaySmall.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                        ),
                )
            },
        )
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Preview
@Composable
fun grazerTopBarPreview() {
    grazerTheme {
        Column {
            grazerTopBar()
        }
    }
}
