package com.jamiescode.grazer.users.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamiescode.grazer.theme.grazerTheme
import com.jamiescode.grazer.users.R
import com.jamiescode.grazer.users.domain.User

@Composable
fun userBio(
    user: User,
    modifier: Modifier,
) {
    Column(
        modifier =
            modifier
                .background(MaterialTheme.colorScheme.secondary)
                .padding(16.dp),
    ) {
        userDiet(user)
        Spacer(modifier = Modifier.size(16.dp))
        userAge(user)
        Spacer(modifier = Modifier.size(16.dp))
        userRelationshipStatus(user)
    }
}

@Composable
private fun userDiet(user: User) {
    userAttributeRow(
        text = user.diet,
        icon =
            UserAttributeIcon.DrawableIcon(
                icon = R.drawable.plant,
                contentDescription = "Diet",
            ),
    )
}

@Composable
private fun userAge(user: User) {
    userAttributeRow(
        text = user.getAge().toString(),
        icon =
            UserAttributeIcon.DrawableIcon(
                icon = R.drawable.cake,
                contentDescription = "Age",
            ),
    )
}

@Composable
private fun userRelationshipStatus(user: User) {
    userAttributeRow(
        text = user.relationshipStatus,
        icon =
            UserAttributeIcon.VectorIcon(
                icon = Icons.Outlined.FavoriteBorder,
                contentDescription = "Relationship Status",
            ),
    )
}

@Composable
private fun userAttributeRow(
    text: String,
    icon: UserAttributeIcon,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val tint = MaterialTheme.colorScheme.onBackground
        when (icon) {
            is UserAttributeIcon.DrawableIcon -> {
                Icon(
                    painter = painterResource(id = icon.icon),
                    contentDescription = icon.contentDescription,
                    tint = tint,
                )
            }
            is UserAttributeIcon.VectorIcon -> {
                Icon(
                    imageVector = icon.icon,
                    contentDescription = icon.contentDescription,
                    tint = tint,
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview
@Composable
fun userBioPreview() {
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
            userBio(
                user = user,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
