package dev.vstd.shoppingcart.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.keego.shoppingcart.R
import dev.vstd.shoppingcart.data.APIErrorUtil
import dev.vstd.shoppingcart.data.remote.user.LoginRequest
import dev.vstd.shoppingcart.data.remote.user.UserService
import dev.vstd.shoppingcart.ui.base.InuFullWidthButton
import dev.vstd.shoppingcart.ui.base.InuTextField
import dev.vstd.shoppingcart.ui.destinations.signup_Destination
import dev.vstd.shoppingcart.utils.toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

@AuthNavGraph(start = true)
@Destination
@Composable
fun login_(navigator: DestinationsNavigator) {
    val hostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = {
        SnackbarHost(hostState) {
            Snackbar(
                snackbarData = it,
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = Color.White
            )
        }
    }) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            body_(navigator, hostState)
        }
    }
}

@Composable
private fun body_(navigator: DestinationsNavigator, hostState: SnackbarHostState) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val userService = (context as AuthActivity).userService
    val scope = rememberCoroutineScope()

    Box {
        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.bg_login),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Good to see you back! ❤",
                modifier = Modifier.padding(top = 12.dp),
                style = MaterialTheme.typography.titleLarge
            )
            InuTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") }
            )
            InuTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text(text = "Password") }
            )
            InuFullWidthButton(
                onClick = {
                    val result =
                        LoginValidator.validate(email, password)
                    if (result.success) {
                        login(userService, email, password)
                    } else {
                        scope.launch { hostState.showSnackbar(result.message) }
                    }
                },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = "Login", fontSize = 20.sp)
            }
            TextButton(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                onClick = {
                    context.toast("This feature is not yet implemented")
                }
            ) {
                Text("Use app without login")
            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    navigator.navigate(signup_Destination)
                }
            ) {
                Text("Sign up")
                Icon(
                    imageVector = Icons.Default.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

private fun login(service: UserService, email: String, password: String) {
    GlobalScope.launch {
        val resp = service.login(
            LoginRequest(
                email = email,
                password = password
            )
        )
        if (resp.isSuccessful) {
            Timber.d("Login successful: ${resp.body()}")
        } else {
            val error = APIErrorUtil.getAPIError(resp)
            Timber.e("Login failed: ${error.status} ${error.message}")
        }
    }
}
