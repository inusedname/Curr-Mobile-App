package dev.vstd.shoppingcart.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.keego.shoppingcart.R

private val RailwayFontFamily = FontFamily(
    Font(R.font.raleway_regular, weight = FontWeight.Normal),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_bold, FontWeight.Bold),
)
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = RailwayFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
)