package com.campusdual_grupo3.bookandgo.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.campusdual_grupo3.bookandgo.R

@Composable
fun TopBarNavigationWrapper() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(50.dp)
        )

        // Search + Filter
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.LightGray.copy(alpha = 0.3f),
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Search, //search logo
                    contentDescription = "Search",
                    tint = Color.Gray
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    placeholder = { Text("Buscar", color = Color.Red) },

                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Icons.AutoMirrored.Filled.List, //filter logo
                    contentDescription = "Filter",
                    tint = Color.Gray
                )
            }
        }
    }
}