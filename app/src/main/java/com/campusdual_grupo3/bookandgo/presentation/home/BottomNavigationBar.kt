package com.campusdual_grupo3.bookandgo.presentation.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.campusdual_grupo3.bookandgo.R

@Composable
fun BottomBarNavigation(navController: NavController) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.logo), contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { /* Navigate to Home */ }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.logo), contentDescription = "Experiences") },
            label = { Text("Experiences") },
            selected = false,
            onClick = { /* Navigate to Experiences */ }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.logo), contentDescription = "Gift Card") },
            label = { Text("Gift Card") },
            selected = false,
            onClick = { /* Navigate to Gift Card */ }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.logo), contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { /* Navigate to Profile */ }
        )
    }
}