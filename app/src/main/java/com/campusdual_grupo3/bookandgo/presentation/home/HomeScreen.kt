package com.campusdual_grupo3.bookandgo.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.campusdual_grupo3.bookandgo.R


@Composable
fun HomeScreen(onClickExperience: (Int) -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            //***** barra superior ******

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp, top = 45.dp, bottom = 5.dp, end = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp)

            )
            Image(painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        // Aquí añadir la lógica para navegar a la sección de perfil
                    }

            )
        }
        Row(
            //***** barra buscador *****
            modifier = Modifier
                .padding(
                    horizontal = 10.dp, vertical = 10.dp
                )
                .fillMaxWidth()
                .background(color = Color.LightGray),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = stringResource(R.string.search_experience),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color.LightGray)
                    .padding(
                        horizontal = 10.dp, vertical = 10.dp
                    )


            )
            Image(
                painter = painterResource(id = R.drawable.ic_search_white),
                contentDescription = null,

                modifier = Modifier
                    .padding(
                        start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp
                    )

                    .background(color = Color.Black)
                    .width(50.dp)

            )

        }
        // Categorías de experiencias
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre elementos
        ) {

            items(uiState.categories) { category ->
                Text(
                    text = category?.name ?: "Explorar",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (uiState.selectedCategory == category) Color.Black else Color.White)//
                        .padding(
                            horizontal = if (uiState.selectedCategory == category) 10.dp else 4.dp,
                            vertical = 2.dp
                        )
                        .clickable {
                            viewModel.onCategorySelected(category)

                        },
                    color = if (uiState.selectedCategory == category) Color.White else Color.Black
                )

            }

        }

        LazyColumn(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
        ) {
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 8.dp)
                    .clickable {
                        // Aquí añadir la lógica para navegar a la sección de exploración
                    }) {
                    if (uiState.selectedCategory == null) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_onboarding_1),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                    } else {
                        AsyncImage(
                            model = uiState.selectedCategory?.image,
                            contentDescription = uiState.selectedCategory?.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                    }

                    Text(
                        text = uiState.selectedCategory?.name ?: "Explorar",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,

                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
            }

            item {
                Text(
                    text = "Experiencias destacadas",
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                LazyRow(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.experiences) { experience ->
                        var isFavorite by remember { mutableStateOf(experience.isFavorite) }
                        Card(
                            modifier = Modifier
                                .width(200.dp)
                                .height(150.dp)
                                .clickable {
                                    onClickExperience(experience.id)
                                }
                        ) {
                            Column {
                                AsyncImage(
                                    model = experience.image,
                                    contentDescription = experience.name,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Row {
                                Text(
                                    text = experience.name,
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 14.sp
                                )




                            Image(painter = painterResource(
                                if (isFavorite) {
                                    R.drawable.ic_favorite
                                } else R.drawable.ic_not_favorite
                            ), // Cambiamos el icono según el estado
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(20.dp)
                                    .clickable {
                                        isFavorite = !isFavorite
                                        // Actualizamos el estado en la experiencia original
                                        viewModel.onFavoriteClicked(experience)

                                    })

                        }
                    }

                }
            }
        }

        item {
            Text(
                text = "Las mejor valoradas",
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }

        items(uiState.betterExperience) { experience ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClickExperience(experience.id)
                    }
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Row(
                    modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    Row(
                        modifier = Modifier.weight(1f, true),
                        verticalAlignment = Alignment.CenterVertically // Alinea verticalmente el texto

                    ) {
                        AsyncImage(
                            model = experience.image,
                            contentDescription = experience.name,
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .size(100.dp)
                                .clip(CircleShape),
                        )
                        Column(
                            // Contenido de texto a la derecha
                            modifier = Modifier.padding(
                                start = 16.dp
                            ),// Espacio entre imagen y texto

                        ) {
                            Text(
                                text = experience.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = experience.description,// Añade una descripción
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    Column( // puntuacion y precio
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 16.dp)
                            .fillMaxHeight(),

                        verticalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    end = 16.dp, start = 8.dp
                                )
                                .background(Color.Yellow)
                                .padding(
                                    8.dp
                                ),


                            text = experience.reviews?.get(index = 0)?.rating.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = experience.price.toString(), // Añade una descripción
                            fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray
                        )
                    }

                }
            }
        }
    }
}
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun HomePreview() {
    HomeScreen() {}

}