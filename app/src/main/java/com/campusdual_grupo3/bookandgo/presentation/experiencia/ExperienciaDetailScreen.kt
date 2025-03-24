package com.campusdual_grupo3.bookandgo.presentation.experiencia

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import androidx.lifecycle.compose.LifecycleEventEffect
import coil.compose.AsyncImage
import com.campusdual_grupo3.bookandgo.R

@Composable
fun ExperienceDetailScreen(
    experienceId: Int, onBackClick: () -> Unit, goToGiftCard: () -> Unit

) {
    val viewModel: ExperienceViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val playfairFont = FontFamily(
        Font(R.font.playfair_regular, FontWeight.Normal)
    )

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.loadExperienceById(experienceId)
    }
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.loadReviewsByExperienceId(experienceId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            //***** barra superior ******

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp, top = 20.dp, bottom = 5.dp, end = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Image(painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onBackClick()
                    })
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp)

            )

        }
        Card(
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            AsyncImage(
                model = uiState.detailExperiences?.image,
                contentDescription = uiState.detailExperiences?.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp, bottom = 5.dp, end = 20.dp,

                        ),
                horizontalArrangement = Arrangement.Center,

                ) {
                Text(
                    text = uiState.detailExperiences?.name ?: "Cargando...",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontFamily = playfairFont),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }//fin card
        LazyColumn(
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
                .align(Alignment.Start)

        ) {

            item {
//                LazyRow(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.White)
//                        .padding(vertical = 8.dp)
//                        .clip(RoundedCornerShape(8.dp)),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//
//                ) {
//                    items(uiState.reviews?.size ?: 0) { index ->
//                        val review = uiState.reviews?.get(index)
//                        Card(
//                            modifier = Modifier
//                                .width(200.dp)
//                                .height(150.dp),
//
//                            shape = RoundedCornerShape(16.dp)
//
//                        ) {
//                            Column(
//                                modifier = Modifier
//                                    .padding(8.dp)
//                                    .align(Alignment.CenterHorizontally)
//
//
//                            ) {
//                                Image(
//                                    painter = painterResource(
//                                        id = R.drawable.ic_positive
//                                    ), contentDescription = null,
//                                    modifier = Modifier
//                                        .padding(8.dp)
//                                )
//                                Text(
//                                    text = review?.comment ?: "Sin comentarios",
//                                    textAlign = TextAlign.Center
//                                )
//
//
//                            }
//
//                        }
//
//                    }
//                }


            }  //reviews
            item{  // precio y giftcard
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, bottom = 16.dp)
                        ,

                    horizontalArrangement =  Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                    text = "Precio " + uiState.detailExperiences?.price.toString() + " €",
                    fontSize = 20.sp,
                    style = TextStyle(fontFamily = playfairFont),
                    fontWeight = FontWeight.Bold
                )
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_giftcard
                        ), contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(30.dp)
                            .clickable {
                                goToGiftCard()
                            }
//
                    )


                }
            }
            item { //detalles experiencias
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF9F9A9A),
                                    Color(0xFFC1C5C7),
                                    Color(0xFFBBBAB2)
                                )
                            )
                        )

                ) {
                    Text(
                        text = uiState.detailExperiences?.description ?: "Cargando...",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Justify,
                        style = TextStyle(fontFamily = playfairFont),
                        modifier = Modifier.padding(8.dp)

                    )

                }

                // cantidad de experiencias disponibles
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 8.dp)
                        .align(Alignment.Start),

                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_place
                        ), contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                    )

                    Text(
                        text = uiState.detailExperiences?.location.toString(),
                        style = TextStyle(fontFamily = playfairFont),
                        modifier = Modifier
                            .padding(8.dp)
                    )

                }
                // fechas
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 8.dp)
                        .align(Alignment.Start),

                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_calendar
                        ), contentDescription = null, modifier = Modifier
                            .padding(8.dp)
                    )

                    Text(
                        text = uiState.detailExperiences?.dateFrom.toString() + " - " + uiState.detailExperiences?.dateTo.toString(),
                        style = TextStyle(fontFamily = playfairFont),
                        modifier = Modifier
                            .padding(8.dp)
                    )


                }

                // Experiencias disponibles
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 8.dp)
                        .align(Alignment.Start),

                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_profile
                        ), contentDescription = null, modifier = Modifier
                            .padding(8.dp)
                    )

                    Text(
                        text = uiState.detailExperiences?.capacity.toString() + " experiencias disponibles",
                        style = TextStyle(fontFamily = playfairFont),
                        modifier = Modifier
                            .padding(8.dp)
                    )


                }
                ///// aqui vendrían más detalles
                Row(){}

            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun ExperienceDetailPreview() {
    ExperienceDetailScreen(experienceId = 1, onBackClick = {}, goToGiftCard = {})
}
