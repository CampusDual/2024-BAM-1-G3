package com.campusdual_grupo3.bookandgo.presentation.experiencia.cards


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun Tag(text: String) {
    Box(
        modifier = Modifier
            .background(Color.Black, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun FavoriteButton(
    initialIsFavorite: Boolean,
    onFavoriteChanged: (Boolean) -> Unit,
    modifier: Modifier,
) {
    // Usamos remember y mutableStateOf para mantener el estado del botón
    val isFavorite = remember { mutableStateOf(initialIsFavorite) }

    Icon(
        painter = painterResource(
            id = if (isFavorite.value) R.drawable.ic_favorite else R.drawable.ic_not_favorite
        ),
        contentDescription = "Favorite",
        tint = if (isFavorite.value) Color.Red else Color.Gray,
        modifier = Modifier
            .clickable {
                // Cambiamos el estado al hacer clic
                isFavorite.value = !isFavorite.value
                //Notificamos el cambio al componente padre.
                onFavoriteChanged(isFavorite.value)
            }
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
fun ExperienceName(name: String, dateTo: String, dateFrom: String) {
    Column {
        Text(
            text = name,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
        )

        Text(
            text = "Desde: $dateTo - $dateFrom",
            color = Color.LightGray,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
        )
    }
}

@Composable
fun ExperienceCardV1(
    experience: ExperienceEntity,
    onFavoriteClick: () -> Unit,
    onViewOfferClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .width(280.dp)
                .height(220.dp)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                /********** BACKGROUND IMAGE **********/
                AsyncImage(
                    model = experience.image, // URL de la imagen desde el backend
                    contentDescription = experience.name,
                    contentScale = ContentScale.Crop, // Ajusta la imagen para cubrir el espacio
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(id = R.drawable.ic_placeholder_image),// painterResource(id = R.drawable.music), // Imagen por defecto
                    error = painterResource(id = R.drawable.ic_error_placeholder_image) // Imagen en caso de error
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
                        val isFavorite = remember { mutableStateOf(false) }
                        Tag(text = experience.category_name.toString())
                        FavoriteButton(
                            modifier = Modifier,
                            initialIsFavorite = true,
                            onFavoriteChanged = {
                                isFavorite.value = it
                            }
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
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
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
        Row {
            ExperienceName(
                name = experience.name.toString(),
                dateTo = "10-Jul-2025",
                dateFrom = "14-Jul-2025"
            )
        }
    }
}