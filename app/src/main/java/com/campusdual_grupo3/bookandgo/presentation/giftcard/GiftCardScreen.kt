package com.campusdual_grupo3.bookandgo.presentation.giftcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import coil.compose.AsyncImage
import com.campusdual_grupo3.bookandgo.R
import com.campusdual_grupo3.bookandgo.presentation.components.CustomSearchBar
import com.campusdual_grupo3.bookandgo.presentation.experiencia.ExperienceViewModel
import com.campusdual_grupo3.bookandgo.presentation.home.HomeViewModel

@Composable
fun GiftCardScreen(
    experienceId: Int?,
    modifier: Modifier = Modifier,
    onClickGiftCard: (Int) -> Unit,
    onProfileClick: () -> Unit,
    onLogoClick: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    var searchText by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val playfairFont = FontFamily(
        Font(R.font.playfair_regular, FontWeight.Normal)
    )

    val experienceViewModel: ExperienceViewModel = hiltViewModel()


    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        experienceViewModel.loadExperienceById(experienceId ?: 1)
    }

    // Top Bar
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 5.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp).clickable { onLogoClick() }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onProfileClick()
                        // Aquí añadir la lógica para navegar a la sección de perfil
                    }
            )
        }

        CustomSearchBar(onValueChange = { newText ->
            searchText = newText
            viewModel.filterExperiences(searchText)
        })

        if (searchText.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                if (uiState.filteredExperiences.isEmpty()) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.experience_not_found),
                                style = TextStyle(fontFamily = playfairFont)
                            )
                        }
                    }
                } else {
                    items(uiState.filteredExperiences) { experience ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onClickGiftCard(experience.id)
                                }
                                .padding(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                            border = BorderStroke(0.5.dp, Color.LightGray)
                        ) {
                            Row(
                                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f, true),
                                    verticalAlignment = Alignment.CenterVertically
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
                                        modifier = Modifier.padding(start = 16.dp),
                                    ) {
                                        experience.name?.let {
                                            Text(
                                                text = it,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold,
                                                style = TextStyle(fontFamily = playfairFont)
                                            )
                                        }
                                        Text(
                                            text = experience.description,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            fontSize = 14.sp,
                                            style = TextStyle(fontFamily = playfairFont),
                                            color = Color.Gray
                                        )
                                    }
                                }

                                Column(
                                    modifier = Modifier
                                        .padding(start = 16.dp, bottom = 16.dp)
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    if (!experience.reviews.isNullOrEmpty()) {
                                        Text(
                                            modifier = Modifier
                                                .padding(end = 16.dp, start = 8.dp)
                                                .background(Color.Yellow)
                                                .padding(8.dp),
                                            text = experience.reviews.get(index = 0).rating.toString(),
                                            fontSize = 16.sp,
                                            style = TextStyle(fontFamily = playfairFont),
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = experience.price.toString() + " €",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            style = TextStyle(fontFamily = playfairFont),
                                            color = Color.Gray
                                        )
                                    } else {
                                        Text(
                                            modifier = Modifier
                                                .padding(
                                                    end = 16.dp,
                                                    start = 2.dp,
                                                    top = 16.dp,
                                                    bottom = 16.dp
                                                )
                                                .align(Alignment.End),
                                            text = experience.price.toString() + " €",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            style = TextStyle(fontFamily = playfairFont),
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {

            // Experience list


            LazyColumn(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {

                items(uiState.betterExperience) { experience ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onClickGiftCard(experience.id)
                            }
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        border = BorderStroke(0.5.dp, Color.LightGray)
                    ) {
                        Row(
                            modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Row(
                                modifier = Modifier.weight(1f, true),
                                verticalAlignment = Alignment.CenterVertically
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
                                    modifier = Modifier.padding(start = 16.dp),
                                ) {
                                    experience.name?.let {
                                        Text(
                                            text = it,
                                            fontSize = 16.sp,
                                            style = TextStyle(fontFamily = playfairFont),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        text = experience.description,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        style = TextStyle(fontFamily = playfairFont),
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp, bottom = 16.dp)
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                if (!experience.reviews.isNullOrEmpty()) {
                                    Text(
                                        modifier = Modifier
                                            .padding(end = 16.dp, start = 8.dp)
                                            .background(Color.Yellow)
                                            .padding(8.dp),
                                        text = experience.reviews.get(index = 0).rating.toString(),
                                        fontSize = 16.sp,
                                        style = TextStyle(fontFamily = playfairFont),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = experience.price.toString(),
                                        fontSize = 20.sp,
                                        style = TextStyle(fontFamily = playfairFont),
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Gray
                                    )
                                } else {
                                    Text(
                                        modifier = Modifier
                                            .padding(
                                                end = 16.dp,
                                                start = 2.dp,
                                                top = 16.dp,
                                                bottom = 16.dp
                                            )
                                            .align(Alignment.End),
                                        text = experience.price.toString() + " €",
                                        style = TextStyle(fontFamily = playfairFont),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }


            // Giftcard

            /*
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = giftCard.titleScreen,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 42.dp)
                )

                GiftCardHeader(
                    experienceImage = giftCard.experienceImage,
                    experienceTitle = giftCard.experienceName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 16.dp)
                )

                GiftCardBody(onPayClick = giftCard.onPayClick)
            }

             */
        }
    }
}