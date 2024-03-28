package dev.vstd.shoppingcart.ui.auth

import android.content.Context
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
import dev.vstd.shoppingcart.utils.toast
import kotlinx.coroutines.launch

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

    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var reconfirmPassword by remember { mutableStateOf("") }

    Column {
        IconButton(onClick = navigator::navigateUp) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
        }
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
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
            val result = SignUpValidator.validate(name, email, password, reconfirmPassword)
            if (result.success) {
                signup(context, email, password)
            } else {
                scope.launch { hostState.showSnackbar(result.message) }
            }
        }) {
            Text(text = "Sign Up")
        }
    }
}

private fun signup(context: Context, username: String, password: String) {
    context.toast("Sign up successful")
}
