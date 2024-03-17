package dev.vstd.shoppingcart.ui.payment.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.vstd.shoppingcart.utils.VietnamAdministrationProvider

@RootNavGraph
@Destination
@Composable
fun select_address_(navigator: DestinationsNavigator) {
    Scaffold(topBar = _topBar(onBack = navigator::navigateUp)) {
        Column(Modifier.padding(it)) {
            body_ { city, district ->
                // TODO
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun body_(onComplete: (VietnamAdministrationProvider.City, VietnamAdministrationProvider.District) -> Unit) {
    val vimel = hiltViewModel<SelectAddressVimel>()
    val cities by vimel.cities.collectAsState()
    val districts by vimel.districts.collectAsState()
    val currentCity by vimel.currentCity.collectAsState()
    val currentDistrict by vimel.currentDistrict.collectAsState()
    var cityExpanded by remember {
        mutableStateOf(false)
    }
    var districtExpanded by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ExposedDropdownMenuBox(expanded = cityExpanded, onExpandedChange = { cityExpanded = it }) {
            TextField(value = currentCity?.displayName ?: "Select",
                onValueChange = { },
                label = { Text(text = "Town/City") })
            DropdownMenu(expanded = cityExpanded,
                onDismissRequest = { cityExpanded = !cityExpanded }) {
                cities.forEach {
                    DropdownMenuItem(text = {
                        Text(text = it.displayName)
                    }, onClick = {
                        vimel.setCurrentCity(it)
                    })
                }
            }
        }
        ExposedDropdownMenuBox(expanded = districtExpanded,
            onExpandedChange = { districtExpanded = it }) {
            TextField(value = currentDistrict?.displayName ?: "Select",
                onValueChange = { },
                label = { Text(text = "Address") })
            DropdownMenu(expanded = districtExpanded,
                onDismissRequest = { districtExpanded = !districtExpanded }) {
                districts.forEach {
                    DropdownMenuItem(text = {
                        Text(text = it.displayName)
                    }, onClick = {
                        vimel.setDistrict(it)
                    })
                }
            }
        }
        Button(
            onClick = {
                onComplete(
                    currentCity!!, currentDistrict!!
                )
            },
            enabled = currentCity != null && currentDistrict != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Save Changes")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun _topBar(onBack: () -> Unit) = @Composable {
    TopAppBar(
        title = { Text(text = "Shipping Address") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}