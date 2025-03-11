package com.campusdual_grupo3.bookandgo.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusdual_grupo3.bookandgo.R

@Composable
fun TitleH1App(text: String, color: Color = Color.Black, size: TextUnit = 24.sp, font: FontFamily = FontFamily(Font(R.font.playfair_bold))) {
    Text(
        text = text,
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 8.dp)
            .fillMaxWidth(),
        fontFamily = font,
        fontSize = size,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        color = color
    )
}