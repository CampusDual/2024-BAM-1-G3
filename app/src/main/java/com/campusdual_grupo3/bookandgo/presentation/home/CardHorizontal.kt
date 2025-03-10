package com.campusdual_grupo3.bookandgo.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campusdual_grupo3.bookandgo.R

@Composable
fun CardHorizontal(experiences: List<Pair<Int, String>>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(experiences.size) { index ->
            val (imageUrl, title) = experiences[index]
            Surface(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .width(150.dp)
                    .height(200.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                shadowElevation = 4.dp
            ) {
                Column {
                    Image(
                        painter = painterResource(id = imageUrl),
                        contentDescription = "Experience Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}