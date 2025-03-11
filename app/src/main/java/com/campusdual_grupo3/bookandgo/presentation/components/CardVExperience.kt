package com.example.presentation.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.campusdual_grupo3.bookandgo.R
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.presentation.theme.GoldenYellow

@Composable
fun Tag(text: String) {
    Box(
        modifier = Modifier
            .background(Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Category $text",
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun FavoriteButton(isFavorite: Boolean, onClick: () -> Unit) {
    Icon(
        painter = painterResource(id = if (isFavorite) com.campusdual_grupo3.bookandgo.R.drawable.ic_favorite else com.campusdual_grupo3.bookandgo.R.drawable.ic_not_favorite),
        contentDescription = "Favorite",
        tint = if (isFavorite) Color.Red else Color.Gray,
        modifier = Modifier
            .clickable { onClick() }
    )
}

@Composable
fun PriceTag(price: String) {
    Text(
        text = "$price€",
        color = Color.White,
        fontSize = 32.sp,
        fontWeight = FontWeight.Black
    )
}

@Composable
fun ExperienceName(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
fun CardVExperience(
    experience: ExperienceEntity,
    onFavoriteClick: () -> Unit,
    onViewOfferClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .width(280.dp)
            .height(180.dp)
            .padding(0.dp, 0.dp, 8.dp, 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            /********** BACKGROUND IMAGE **********/
            AsyncImage(
                model = experience.image, // URL de la imagen desde el backend
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop, // Ajusta la imagen para cubrir el espacio
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.ic_default_image_explorer_category), // Imagen por defecto
                error = painterResource(id = R.drawable.ic_default_image_explorer_category) // Imagen en caso de error
            )

            /********** GRADIENT BACKGROUND **********/
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 100f,
                            endY = 800f
                        )
                    )
            )

            /********** CARD CONTENT **********/
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                // Fila superior: Tag y botón de favorito
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Tag(text = experience.category.toString())
                    FavoriteButton(
                        isFavorite = experience.isFavorite,
                        onClick = onFavoriteClick
                    )
                }

                Spacer(modifier = Modifier.height(64.dp))

                /********** BOTTOM ROW (Price, Button) **********/
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PriceTag(price = experience.price.toString())
                    Button(
                        onClick = onViewOfferClick,
                        colors = ButtonDefaults.buttonColors(containerColor = GoldenYellow),
                        shape = RoundedCornerShape(8.dp)

                    ) {
                        Text(
                            text = "Ver oferta",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                }

            }
        }

    }

    /********** EXPERIENCE NAME **********/
    ExperienceName(name = experience.name)
}