package dev.vstd.shoppingcart.ui.payment.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.vstd.shoppingcart.ui.payment.checkout.destinations.select_address_Destination
import dev.vstd.shoppingcart.ui.payment.ui.theme.startPadding

@RootNavGraph(start = true)
@Destination
@Composable
fun checkout_(navigator: DestinationsNavigator) {
    val products = listOf(Product.getFakeProduct())
    Scaffold(topBar = topBar(onBack = {/*TODO*/ })) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                _shipping_address(text = "Nguyễn Viết Quang | (+84) 111 111 222\nChung cư New Skyline\nPhường Văn Quán, Quận Hà Đông, Hà Nội",
                    onClick = {
                        navigator.navigate(select_address_Destination)
                    })

                _purchasing_list(products = products)

                _shipping_option()


                _voucher_and_purchase_option {
                    // TODO
                }

                _total_summary("28.000d", "15.000d", "43.000d")
            }
            _cta(
                Modifier
                    .height(64.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                sum = "28.000đ",
                onClickCta = {
                    // TODO
                })
        }
    }
}

@Composable
private fun _total_summary(tienHang: String, tienVanChuyen: String, tongThanhToan: String) {
    Column(Modifier.padding(startPadding), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.EventNote, contentDescription = null)
            Text(text = "Chi tiet thanh toan", modifier = Modifier.padding(start = 8.dp))
        }
        Row {
            Text(text = "Tổng tiền hàng")
            Spacer(Modifier.weight(1f))
            Text(text = tienHang)
        }
        Row {
            Text(text = "Phí vận chuyển")
            Spacer(Modifier.weight(1f))
            Text(text = tienVanChuyen)
        }
        Row {
            Text(text = "Tổng thanh toán")
            Spacer(Modifier.weight(1f))
            Text(
                text = tongThanhToan,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun _voucher_and_purchase_option(onClick: () -> Unit) {
    Row(
        Modifier
            .clickable(onClick = onClick)
            .padding(startPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Rounded.MonetizationOn, contentDescription = null)
        Text(text = "Phương thức thanh toán", modifier = Modifier.padding(start = 8.dp))
        Spacer(Modifier.weight(1f))
        Text(text = "Ví ShopeePay")
        Icon(Icons.Default.ArrowForwardIos, null, tint = Color.LightGray)
    }
}

@Composable
private fun _shipping_option() {

}

@Composable
private fun _purchasing_list(products: List<Product>) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(startPadding)
        ) {
            Text(
                text = "Yêu thích",
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 2.dp, horizontal = 4.dp),
                color = Color.White
            )

            Text(
                text = "Trendfronts.vn",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column {
            for (product in products) {
                Row(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(12.dp)
                ) {
                    AsyncImage(
                        model = product.image,
                        contentDescription = null,
                        modifier = Modifier.size(72.dp)
                    )
                    Column {
                        Text(text = product.title)
                        Text(text = product.category)
                        Row {
                            Text(text = product.price)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = "x${product.quantity}")
                        }
                    }
                }
                Divider()
            }
        }
    }
}

@Composable
private fun _shipping_address(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(startPadding),
    ) {
        Icon(
            imageVector = Icons.Default.PinDrop,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Column(Modifier.padding(start = 8.dp)) {
            Text(text = "Địa chỉ nhận hàng")
            Text(text = text)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.Default.ArrowForwardIos,
            null,
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun _cta(modifier: Modifier = Modifier, sum: String, onClickCta: () -> Unit) {
    Row(modifier) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(7f)
                .fillMaxHeight()
        ) {
            Text(text = "Tổng thanh toán")
            Text(text = sum, style = MaterialTheme.typography.titleLarge)
        }
        Box(
            modifier = Modifier
                .weight(3f)
                .clickable(onClick = onClickCta)
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Đặt hàng",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBar(onBack: () -> Unit) = @Composable {
    TopAppBar(title = { Text(text = "Checkout") }, navigationIcon = {
        IconButton(
            onClick = onBack
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    })
}