package com.campusdual_grupo3.bookandgo.presentation.profile

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import coil.compose.AsyncImage
import com.campusdual_grupo3.bookandgo.R

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onLogoutSuccess: () -> Unit
) {
    val userProfile by viewModel.userProfile.collectAsState()
    val playfairFont = FontFamily(Font(R.font.playfair_regular, FontWeight.Normal))

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.loadUserProfile()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            //***** barra superior ******

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp, top = 20.dp, bottom = 5.dp, end = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween

            ) {
            Image(painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onBackClick()
                    })
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding( bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Datos personales",
                style = TextStyle(fontFamily = playfairFont, fontSize = 32.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 32.dp)
            )
            // Imagen de perfil obtenida desde la URL del usuario
            AsyncImage(
                model = userProfile?.image?.toUri() ?: R.drawable.ic_profile_pic,
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                error = painterResource(id = R.drawable.ic_profile_pic)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre del usuario
            Text(
                text = userProfile?.name ?: "Cargando nombre...",
                style = TextStyle(fontFamily = playfairFont, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Email
            Text(
                text = userProfile?.email ?: "Cargando email...",
                style = TextStyle(fontFamily = playfairFont, fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Dirección
            Text(
                text = userProfile?.address ?: "Cargando dirección...",
                style = TextStyle(fontFamily = playfairFont, fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Teléfono
            Text(
                text = userProfile?.phone ?: "Cargando teléfono...",
                style = TextStyle(fontFamily = playfairFont, fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.logout(onLogoutSuccess) }, // Llamamos a logout()
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Log out", style = TextStyle(fontFamily = playfairFont, color = Color.Black))
            }}

    }
}