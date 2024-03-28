package dev.vstd.shoppingcart.ui.auth

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.vstd.shoppingcart.ui.destinations.signup_Destination
import dev.vstd.shoppingcart.utils.toast

@AuthNavGraph(start = true)
@Destination
@Composable
fun login_(navigator: DestinationsNavigator) {
    Scaffold() { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            body_(navigator)
        }
    }
}

@Composable
private fun body_(navigator: DestinationsNavigator) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column {
        TextField(value = username, onValueChange = { username = it }, label = { Text(text = "Username") })
        TextField(value = password, onValueChange = { password = it }, label = { Text(text = "Password") })
        Button(onClick = { login(context, username, password) }) {
            Text("Login")
        }
        Text(text = "Sign up", textDecoration = TextDecoration.Underline, modifier = Modifier.clickable {
            navigator.navigate(signup_Destination)
        })
        Text(text = "Use app without login", textDecoration = TextDecoration.Underline, modifier = Modifier.clickable {
            context.toast("This feature is not yet implemented")
        })
    }
}

private fun login(context: Context, username: String, password: String) {
    context.toast("TODO: Not yet implemented")
}
