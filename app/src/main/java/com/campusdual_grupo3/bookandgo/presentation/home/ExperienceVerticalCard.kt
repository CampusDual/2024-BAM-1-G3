package com.campusdual_grupo3.bookandgo.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.campusdual_grupo3.bookandgo.R

@Composable
fun ExperienceVerticalCard(
    imageRes: Int,
    title: String,
    rating: Float,
    location: String,
    price: String,
    duration: String
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column (modifier = Modifier.padding(16.dp)) {
            // Image
            Box(modifier = Modifier.aspectRatio(16f / 9f)) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                FloatingActionButton (
                    onClick = { /* Add to favorites */ },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                }
            }

            // Rating
            Row (verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.logo), //estrella
                    contentDescription = "Rating",
                    tint = Color.Yellow
                )
                Text(text = "$rating", style = MaterialTheme.typography.bodyMedium)
            }

            // Description
            Text(text = title, style = MaterialTheme.typography.titleMedium)

            // Duration / Date
            Text(text = duration, style = MaterialTheme.typography.bodySmall)

            // Location
            Text(text = location, style = MaterialTheme.typography.bodySmall)

            // Price
            Text(text = price, style = MaterialTheme.typography.titleSmall)
        }
    }
}