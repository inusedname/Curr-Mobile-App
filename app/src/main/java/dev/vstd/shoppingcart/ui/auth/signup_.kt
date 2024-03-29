package dev.vstd.shoppingcart.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.vstd.shoppingcart.data.APIErrorUtil
import dev.vstd.shoppingcart.data.remote.user.SignupRequest
import dev.vstd.shoppingcart.data.remote.user.UserService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

@AuthNavGraph
@Destination
@Composable
fun signup_(navigator: DestinationsNavigator) {
    val hostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = {
        SnackbarHost(hostState = hostState) {
            Snackbar(
                snackbarData = it,
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = Color.White
            )
        }
    }) {
        Column(Modifier.padding(it)) {
            body_(hostState, navigator)
        }
    }
}

@Composable
private fun body_(hostState: SnackbarHostState, navigator: DestinationsNavigator) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userService = (context as AuthActivity).userService

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var reconfirmPassword by remember { mutableStateOf("") }

    Column {
        IconButton(onClick = navigator::navigateUp) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
        }
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text("Password") }
        )
        TextField(
            value = reconfirmPassword,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { reconfirmPassword = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text("Reconfirm Password") }
        )
        Button(onClick = {
            val result = SignUpValidator.validate(username, email, password, reconfirmPassword)
            if (result.success) {
                signup(userService, email = email, username = username, password)
            } else {
                scope.launch { hostState.showSnackbar(result.message) }
            }
        }) {
            Text(text = "Sign Up")
        }
    }
}

private fun signup(userService: UserService, email: String, username: String, password: String) {
    GlobalScope.launch {
        val resp = userService.signup(
            SignupRequest(
                email = email,
                username = username,
                password = password
            )
        )
        if (resp.isSuccessful) {
            Timber.d("Signup successful: ${resp.body()}")
        } else {
            val error = APIErrorUtil.getAPIError(resp)
            Timber.e("Signup failed: ${error.status} ${error.message}")
        }
    }
}
