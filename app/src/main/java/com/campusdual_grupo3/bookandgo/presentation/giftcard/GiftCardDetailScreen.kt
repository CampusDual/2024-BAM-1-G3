package com.campusdual_grupo3.bookandgo.presentation.giftcard


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    var subject by remember { mutableStateOf("Tarjeta de Regalo Book&Go") }
    var text by remember { mutableStateOf("") }
    var recipientName by remember { mutableStateOf("") }

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
        Spacer(modifier = Modifier.height(16.dp))

        GiftCardHeader(
            experienceImage = uiStateExp.detailExperiences?.image ?: "",
            experienceTitle = uiStateExp.detailExperiences?.name ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = recipientName,
            onValueChange = { recipientName = it },
            label = { Text(stringResource(R.string.giftcard_input_name),  style = TextStyle(fontFamily = playfairFont)) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = to,
            onValueChange = { to = it },
            label = { Text(stringResource(R.string.giftcard_input_email),  style = TextStyle(fontFamily = playfairFont)) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(stringResource(R.string.giftcard_input_message),  style = TextStyle(fontFamily = playfairFont)) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(100.dp),
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(32.dp))

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
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.giftcard_button_checkout),
                style = TextStyle(fontFamily = playfairFont),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}