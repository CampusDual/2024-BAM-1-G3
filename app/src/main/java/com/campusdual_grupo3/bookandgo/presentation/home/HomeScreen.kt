package com.campusdual_grupo3.bookandgo.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.campusdual_grupo3.bookandgo.R

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        TopBarNavigationWrapper()

        // Featured Experiences
        Text(
            text = "Experiencias Destacadas",
            fontFamily = FontFamily(Font(R.font.playfair_bold)),
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        CardHorizontal(
            experiences = listOf(
                R.drawable.ic_onboarding_1 to "Aventura en la Montaña",
                R.drawable.ic_onboarding_2 to "Exploración Submarina",
                R.drawable.ic_onboarding_3 to "Tour Cultural"
            )
        )

        // Vertical Cards
        CardVertical(
            imageUrl = R.drawable.ic_onboarding_1,
            title = "Aventura en la Montaña",
            location = "Bogotá, Colombia",
            price = "$150",
            rating = 4.5f,
            onFavoriteClick = { /* Handle Favorite Click */ }
        )

        // Bottom Bar
        /* BottomBarNavigation(navController = rememberNavController()) */
    }
}