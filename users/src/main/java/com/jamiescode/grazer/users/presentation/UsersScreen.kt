package com.jamiescode.grazer.users.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
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
                    OutlinedCard(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(start = 32.dp, top = 32.dp, end = 32.dp, bottom = 16.dp),
                    ) {
                        Column {
                            Text(
                                text = user.name,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                textAlign = TextAlign.Center,
                            )
                            userImage(
                                imageUrl = user.profileImageUrl,
                                modifier = Modifier.fillMaxWidth(),
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            userBio(
                                user = user,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
            this@Column.AnimatedVisibility(showHeartAnimation) {
                oneTimeLottieAnimation(
                    resource = LottieCompositionSpec.RawRes(R.raw.heart),
                    onFinish = { showHeartAnimation = false },
                )
            }
        }
        cardButtons()
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
private fun heartAnimation(onFinish: () -> Unit) {



    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    var isPlaying by remember { mutableStateOf(true) }
    val progress by animateLottieCompositionAsState(composition, isPlaying)

    if (progress == 1f) {
        onFinish()
        isPlaying = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition= composition,
            progress = { progress }
        )
    }
}

@Composable
private fun cardButtons() {
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
