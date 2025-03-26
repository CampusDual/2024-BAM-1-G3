package com.campusdual_grupo3.bookandgo.presentation.giftcard


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
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
import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardMailDto
import com.campusdual_grupo3.bookandgo.presentation.components.giftcard.GiftCardBody
import com.campusdual_grupo3.bookandgo.presentation.components.giftcard.GiftCardHeader
import com.campusdual_grupo3.bookandgo.presentation.experiencia.ExperienceViewModel

@Composable
fun GiftCardDetailScreen(
    experienceId: Int,
    onBackClick: () -> Unit,
    goToGiftCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: GiftCardDetailViewModel = hiltViewModel()
    val viewModelExp: ExperienceViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val uiStateExp by viewModelExp.uiState.collectAsState()

    var from by remember { mutableStateOf("bookandgo@yopmail.com") }
    var to by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("Regala una experiencia") }
    var text by remember { mutableStateOf("") }

    val playfairFont = FontFamily(
        Font(R.font.playfair_regular, FontWeight.Normal)
    )
    // Keep track of the current experience ID
    val currentExperienceId = rememberUpdatedState(experienceId)

    // Clear previous data and load new experience when ID changes
    LaunchedEffect(currentExperienceId.value) {
        viewModelExp.clearDetailExperience()
        viewModelExp.loadExperienceById(currentExperienceId.value)
    }

    // Still keep the lifecycle event to refresh data when returning
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModelExp.loadExperienceById(experienceId)
    }



    Column(
        modifier = modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable { onBackClick() }
        )

        Text(
            text = "Regalar Experiencia",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp),
            style = TextStyle(fontFamily = playfairFont),
            fontSize = 28.sp
        )

        GiftCardHeader(
            experienceImage = uiStateExp.detailExperiences?.image ?: "",
            experienceTitle = uiStateExp.detailExperiences?.name ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = to,
            onValueChange = { to = it },
            placeholder = { Text("Correo del destinatario", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Mensaje personalizado", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(120.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val giftCardMailDto = GiftCardMailDto(
                    from = from,
                    to = to,
                    subject = subject,
                    text = text
                )
                viewModel.sendGiftCard(giftCardMailDto)
                goToGiftCard()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Enviar Regalo",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

//        GiftCardBody(onPayClick = { goToGiftCard() })
    }
}