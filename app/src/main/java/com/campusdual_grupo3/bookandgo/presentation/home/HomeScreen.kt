package com.campusdual_grupo3.bookandgo.presentation.home

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.Brush
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
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ExperienceDto
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.presentation.experiencia.cards.ExperienceCardV1
import java.text.DateFormat
import java.time.LocalDate


@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val experience = ExperienceEntity(
        id = 1,
        name = "Experiencia 1",
        description = "Descripción de la experiencia 1",
        price = 100,
        duration = 3,
        dateTo = LocalDate.parse("2023-12-31"),
        dateFrom = LocalDate.parse("2023-12-01"),
        location = "Ubicación de la experiencia 1",
        capacity = 45000,
        stock = 45000,
        availability = "ON",
        reviews = listOf(),
        category_id = 2,
        category_name = "Categoría 1",
        isFavorite = true,
        image = "https://static.booking.weekendesk.fr/image_cache/A2005000/2005563/2005563_640_360_FSImage_1_EDIT_POOL_01.jpg",
        user_id = 2,
        createdAt = LocalDate.now()
    )

    Row {
        ExperienceCardV1(experience = experience,
            onFavoriteClick = {},
            onViewOfferClick = {},
            modifier = Modifier
        )
    }



}

@Preview(showBackground = true, showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun HomePreview() {
    HomeScreen(
    )
}