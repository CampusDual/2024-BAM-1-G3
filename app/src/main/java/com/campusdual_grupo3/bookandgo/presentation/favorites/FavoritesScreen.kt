package com.campusdual_grupo3.bookandgo.presentation.favorites

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.bumptech.glide.Glide.init
import com.campusdual_grupo3.bookandgo.R

@Composable
fun FavoritesScreen(
    goToExperienceDetail: (Int) -> Unit,
    goToGiftCard: () -> Unit
) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val playfairFont = FontFamily(
        Font(R.font.playfair_regular, FontWeight.Normal)
    )







//    LaunchedEffect (!uiState.isLoading) {
//        viewModel.loadFavorites()
////        val observer = LifecycleEventObserver { _, event ->
////            if (event == Lifecycle.Event.ON_START) {
////                viewModel.loadFavorites()
////            }
////            if (event == Lifecycle.Event.ON_RESUME) {
////                viewModel.loadFavorites()
////            }
////        }
////        lifecycleOwner.lifecycle.addObserver(observer)
//
//    }

    LaunchedEffect(Unit
    ) {
    viewModel.
    loadFavorites()
    }





    Column() {
        Text(
            text = "Tus experiencias favoritas",
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
                .fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontFamily = playfairFont),
            textAlign = TextAlign.Center
        )

        LazyColumn() {


            items(uiState.favoritesExperiences, key = { favorites ->
                favorites.id }) { favorites ->
                Card(
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                        .height(220.dp)
                        .fillMaxWidth()
                        .clickable {
                            goToExperienceDetail(favorites.id)
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    border = BorderStroke(0.5.dp, Color.LightGray)
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp, end = 16.dp, start = 8.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            favorites.name?.let {

                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .padding(8.dp),
                                    fontSize = 16.sp,
                                    style = TextStyle(fontFamily = playfairFont),
                                    fontWeight = FontWeight.Bold


                                )
                            }
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
                                .padding(bottom = 8.dp, start = 16.dp, end = 8.dp),
                            style = TextStyle(fontFamily = playfairFont),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Precio " + favorites.price.toString() + "€",
                            modifier = Modifier
                                .padding(bottom = 8.dp, start = 16.dp, end = 8.dp),
                            style = TextStyle(fontFamily = playfairFont),
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