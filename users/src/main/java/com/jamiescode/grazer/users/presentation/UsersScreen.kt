package com.jamiescode.grazer.users.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.jamiescode.grazer.theme.grazerTheme
import com.jamiescode.grazer.users.R
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
    val swipeUsers = users.toMutableList()
    var showHeartAnimation by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
    ) {
        Box(Modifier.weight(1f)) {
            noUsersMessage()
            swipeUsers.forEach { user ->
                swipeCard(
                    onSwipeRight = {
                        showHeartAnimation = true
                    },
                ) {
                    userCard(user)
                }
            }
            this@Column.AnimatedVisibility(showHeartAnimation) {
                oneTimeLottieAnimation(
                    resource = LottieCompositionSpec.RawRes(R.raw.heart),
                    onFinish = { showHeartAnimation = false },
                )
            }
        }
        userCardButtons()
    }
}

@Composable
private fun noUsersMessage() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "There are no more users to swipe. Try again later.",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )
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

@Preview
@Composable
fun loadingContentPreview() {
    grazerTheme {
        Column {
            loadingContent()
        }
    }
}

@Preview
@Composable
fun usersContentPreview() {
    val user =
        User(
            name = "Joe Bloggs",
            dateOfBirthEpochSeconds = 946684800L,
            diet = "Vegan",
            profileImageUrl = "https://thispersondoesnotexist.com/",
            relationshipStatus = "Single",
        )
    grazerTheme {
        Column {
            usersContent(listOf(user))
        }
    }
}

@Preview
@Composable
fun noUsersMessagePreview() {
    grazerTheme {
        Column {
            noUsersMessage()
        }
    }
}

@Preview
@Composable
fun errorContentPreview() {
    grazerTheme {
        Column {
            errorContent()
        }
    }
}
