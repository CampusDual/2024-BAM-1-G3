package com.campusdual_grupo3.bookandgo.presentation.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.campusdual_grupo3.bookandgo.R

@Composable
fun FavoritesScreen(
    goToExperienceDetail: (Int) -> Unit,
    goToGiftCard: () -> Unit
) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    Column() {
        Text(
            text = "Tus experiencias Favoritas",
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        LazyColumn() {
            items(uiState.favoritesExperiences) { favorites ->
                Card(
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                        .fillMaxWidth()
                        .clickable {
                            goToExperienceDetail(favorites.id)
                        }
                ) {
                    Column {
                        AsyncImage(
                            model = favorites.image,
                            contentDescription = favorites.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            contentScale = ContentScale.Crop
                        )
                        Row {
                            Text(
                                text = favorites.name,
                                modifier = Modifier
                                    .padding(8.dp),
                                fontSize = 14.sp
                            )
                            Image(painter = painterResource(
                                id = R.drawable.ic_giftcard),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        goToGiftCard()
                                    }
                            )
                        }

                        Text(
                            text = favorites.description,
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Precio " + favorites.price.toString() + "€",
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }


}// fin de FavoritesScreen


@Preview(showBackground = true, showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun FavoritesPreview() {
    FavoritesScreen(
        goToExperienceDetail = {},
        goToGiftCard = {}
    )
}