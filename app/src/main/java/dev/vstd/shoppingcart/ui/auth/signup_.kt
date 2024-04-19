package dev.vstd.shoppingcart.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import dev.vstd.shoppingcart.dataMock.repository.Response
import dev.vstd.shoppingcart.dataMock.repository.UserRepository
import dev.vstd.shoppingcart.theme.ButtonRadius
import dev.vstd.shoppingcart.ui.base.InuFullWidthButton
import dev.vstd.shoppingcart.ui.base.InuTextField
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
    val userRepository = (context as AuthActivity).userRepository

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var reconfirmPassword by remember { mutableStateOf("") }

//    Column {
//        IconButton(onClick = navigator::navigateUp) {
//            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
//        }
//
//        Button(onClick = {
//            val result = SignUpValidator.validate(username, email, password, reconfirmPassword)
//            if (result.success) {
//                signup(userService, email = email, username = username, password)
//            } else {
//                scope.launch { hostState.showSnackbar(result.message) }
//            }
//        }) {
//            Text(text = "Sign Up")
//        }
//    }
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.bg_signup),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Create\nAccount",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 96.dp, start = 16.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomCenter)
        ) {
            InuTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            InuTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )
            InuTextField(
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            InuTextField(
                value = reconfirmPassword,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { reconfirmPassword = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text("Reconfirm Password") },
                modifier = Modifier.fillMaxWidth()
            )
            InuFullWidthButton(
                onClick = {
                    val result =
                        SignUpValidator.validate(username, email, password, reconfirmPassword)
                    if (result.success) {
                        signup(userRepository, email = email, username = username, password) {
                            navigator.navigateUp()
                        }
                    } else {
                        scope.launch { hostState.showSnackbar(result.message) }
                    }
                }
            ) {
                Text(text = "Sign Up", fontSize = 20.sp)
            }
            TextButton(
                onClick = { navigator.navigateUp() },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(ButtonRadius)
            ) {
                Text(text = "Cancel")
            }
        }
    }
}

private fun signup(userService: UserRepository, email: String, username: String, password: String, onSuccess: () -> Unit) {
    GlobalScope.launch {
        val resp = userService.signUp(
            username = username,
            email = email,
            password = password
        )
        if (resp.isSuccessful) {
            Timber.d("Signup successful")
            onSuccess()
        } else {
            val error = (resp as Response.Failed).message
            Timber.e("Signup failed: $error")
        }
    }
}
