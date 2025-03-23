package com.campusdual_grupo3.bookandgo.presentation.giftcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardScreenDto
import com.campusdual_grupo3.bookandgo.presentation.components.giftcard.GiftCardBody
import com.campusdual_grupo3.pruebas.components.giftcard.GiftCardHeader

@Composable
fun GiftCardScreen(
    modifier: Modifier = Modifier,
    giftCard: GiftCardScreenDto
) {
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
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp)
        )

        GiftCardBody(onPayClick = giftCard.onPayClick)
    }
}