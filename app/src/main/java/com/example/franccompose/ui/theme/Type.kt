package com.example.franccompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.franccompose.R // pastikan package-nya benar

// 🔤 FontFamily Righteous
val RighteousFont = FontFamily(
    Font(R.font.righteous_regular, FontWeight.Normal)
)

// ✅ Typography dengan RighteousFont
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = RighteousFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = RighteousFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = RighteousFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = RighteousFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)
