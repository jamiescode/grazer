package com.jamiescode.grazer.users.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamiescode.grazer.theme.grazerTheme
import com.jamiescode.grazer.users.domain.User

@Composable
fun userCard(user: User) {
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

@Preview
@Composable
fun userCardPreview() {
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
            userCard(user)
        }
    }
}
