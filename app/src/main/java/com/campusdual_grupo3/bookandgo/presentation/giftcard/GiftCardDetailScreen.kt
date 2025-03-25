package com.campusdual_grupo3.bookandgo.presentation.giftcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.campusdual_grupo3.bookandgo.R
import com.campusdual_grupo3.bookandgo.presentation.components.giftcard.GiftCardBody
import com.campusdual_grupo3.bookandgo.presentation.components.giftcard.GiftCardHeader
import com.campusdual_grupo3.bookandgo.presentation.experiencia.ExperienceViewModel


@Composable
fun GiftCardDetailScreen(
    experienceId: Int, onBackClick: () -> Unit, goToGiftCard: () -> Unit, modifier: Modifier = Modifier
) {
    val viewModel: ExperienceViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val playfairFont = FontFamily(
        Font(R.font.playfair_regular, FontWeight.Normal)
    )

    // Keep track of the current experience ID
    val currentExperienceId = rememberUpdatedState(experienceId)

    // Clear previous data and load new experience when ID changes
    LaunchedEffect(currentExperienceId.value) {
        viewModel.clearDetailExperience()
        viewModel.loadExperienceById(currentExperienceId.value)
    }

    // Still keep the lifecycle event to refresh data when returning
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.loadExperienceById(experienceId)
    }


    Column (
        modifier = modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    onBackClick()
                })

        Text(
            text = "Regalar Experiencia",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 42.dp),
            style = TextStyle(fontFamily = playfairFont),
            fontSize = 32.sp
        )

        GiftCardHeader(
            experienceImage = uiState.detailExperiences?.image ?: "",
            experienceTitle = uiState.detailExperiences?.name ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp)
        )

        GiftCardBody (onPayClick = { goToGiftCard() })
    }
}