package com.jamiescode.grazer.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun userListScreen(users: List<String> = listOf("user1", "user2", "user3")) {
    val listState = rememberLazyListState()
    LazyColumn(modifier = Modifier, state = listState, contentPadding = PaddingValues(bottom = 8.dp)) {
        items(items = users) { user ->
            Row {
                Text(text = user)
            }
        }
    }
}
