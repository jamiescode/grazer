package com.jamiescode.grazer.users.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jamiescode.grazer.users.R
import com.jamiescode.grazer.users.domain.User

@Composable
fun userBio(
    user: User,
    modifier: Modifier,
) {
    val bioSpacing = 6.dp
    Column(modifier = modifier) {
        Text(
            text = user.name,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.size(bioSpacing))
        Column(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(8.dp),
        ) {
            userDiet(diet = user.diet)
            Spacer(modifier = Modifier.size(bioSpacing))
            userAge(age = user.getAge())
            Spacer(modifier = Modifier.size(bioSpacing))
            userRelationshipStatus(relationshipStatus = user.relationshipStatus)
        }
    }
}

@Composable
private fun userDiet(diet: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.plant),
            contentDescription = "Diet",
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = diet,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun userAge(age: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.cake),
            contentDescription = "Age",
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = age.toString(),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun userRelationshipStatus(relationshipStatus: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Age",
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = relationshipStatus,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.weight(1f),
        )
    }
}
