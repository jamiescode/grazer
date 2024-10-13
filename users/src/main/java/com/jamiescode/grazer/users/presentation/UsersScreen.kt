package com.jamiescode.grazer.users.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.jamiescode.grazer.users.domain.User

@Composable
fun usersScreen(viewModel: UsersViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    val state =
        viewModel.stateLiveData.asFlow().collectAsState(
            initial = UsersViewModel.State.Loading,
        )

    when (val value = state.value) {
        is UsersViewModel.State.Loading -> {
            loadingContent()
        }
        is UsersViewModel.State.Loaded -> {
            usersContent(value.users)
        }
        is UsersViewModel.State.Error -> {
            errorContent()
        }
    }
}

@Composable
fun usersContent(users: List<User>) {
    val listState = rememberLazyListState()
    LazyColumn(modifier = Modifier, state = listState, contentPadding = PaddingValues(bottom = 8.dp)) {
        items(items = users) { user ->
            Row(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Composable
private fun errorContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Something went wrong",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
private fun loadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
