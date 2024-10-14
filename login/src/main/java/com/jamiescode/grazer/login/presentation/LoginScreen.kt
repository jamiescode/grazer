package com.jamiescode.grazer.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.jamiescode.grazer.theme.grazerTheme

@Composable
fun loginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val state =
        viewModel.stateLiveData.asFlow().collectAsState(
            initial = LoginViewModel.State.Idle,
        )

    when (val value = state.value) {
        is LoginViewModel.State.Idle -> {
            loginContent { username: String, password: String ->
                viewModel.login(username, password)
            }
        }
        is LoginViewModel.State.Error -> {
            loginContent(value) { username: String, password: String ->
                viewModel.login(username, password)
            }
        }
        is LoginViewModel.State.Loading -> {
            loadingContent()
        }
    }
}

@Composable
private fun loginContent(
    error: LoginViewModel.State.Error? = null,
    onLogin: (username: String, password: String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        textHeadings()
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyLarge,
            value = email,
            onValueChange = { newText -> email = newText },
            maxLines = 1,
            label = { inputLabel("Email") },
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyLarge,
            value = password,
            onValueChange = { newText -> password = newText },
            maxLines = 1,
            visualTransformation = PasswordVisualTransformation(),
            label = { inputLabel("Password") },
            supportingText = {
                val errorMessage = error?.message ?: ""
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                onLogin(email.text, password.text)
                // Reset UI
                email = TextFieldValue("")
                password = TextFieldValue("")
                keyboardController?.hide()
            },
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun textHeadings() {
    Column {
        Text(
            text = "Welcome to Grazer",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "THE HOME OF PLANT-BASED CONNECTION",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun inputLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
    )
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
fun loginContentPreview() {
    grazerTheme {
        Column {
            loginContent(
                error = LoginViewModel.State.Error("Something went wrong"),
                onLogin = { _, _ -> },
            )
        }
    }
}

@Preview
@Composable
fun textHeadingsPreview() {
    grazerTheme {
        Column {
            textHeadings()
        }
    }
}

@Preview
@Composable
fun inputLabelPreview() {
    grazerTheme {
        Column {
            inputLabel("Email")
        }
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
