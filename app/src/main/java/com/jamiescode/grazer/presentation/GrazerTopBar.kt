package com.jamiescode.grazer.presentation

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.jamiescode.grazer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun grazerTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
            )
        },
    )
}
