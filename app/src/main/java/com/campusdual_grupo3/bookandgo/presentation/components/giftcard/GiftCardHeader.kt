package com.campusdual_grupo3.pruebas.components.giftcard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.campusdual_grupo3.bookandgo.R

@Composable
fun GiftCardHeader(
    experienceImage: String,
    experienceTitle: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        /* ---------- Experience Image ↓ ---------- */
        AsyncImage(
            model = experienceImage,
            contentDescription = experienceTitle,
            modifier = Modifier
                .fillMaxSize()
                .height(260.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )


        // Bottom gradient
        Canvas (modifier = Modifier.matchParentSize().clip(shape = RoundedCornerShape(8.dp))) {
            val gradient = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent, // Transparente en la parte superior
                    Color.Black.copy(alpha = 0.65f) // Oscuro en la parte inferior
                )
            )
            drawRect(brush = gradient)
        }

        /* ---------- Bow Gift Card Image ↓ ---------- */
        Image(
            painter = painterResource(R.drawable.bow),
            contentDescription = null,
            modifier = Modifier.size(180.dp).padding(bottom = 72.dp)
        )

        /* ---------- Experience Name ↓ ---------- */
        Text(
            text = experienceTitle,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 16.sp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp)
        )
    }
}