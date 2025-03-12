package com.campusdual_grupo3.bookandgo.presentation.listing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.campusdual_grupo3.bookandgo.R


@Composable
fun ListScreen(
    onExperienceClick: (Int) -> Unit

){
    val viewModel: ListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            //***** barra superior ******

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp, top = 45.dp, bottom = 5.dp, end = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp)

            )
            Image(painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        // Aquí añadir la lógica para navegar a la sección de perfil
                    }

            )
        }
        Text(
            text = "Tus preferencias",
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center // Alinea el texto al centro
        )
        LazyColumn() {
            items(uiState.listAllExperiences) { experience ->
                Card(
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                        .fillMaxWidth()
                        .clickable {
                            onExperienceClick(experience.id)

                        }
                ) {
                    Column {

                       AsyncImage(
                                model = experience.image,
                        contentDescription = experience.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentScale = ContentScale.Crop
                        )
                        Text(
                            text = experience.name,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(8.dp)
                                ,
                            fontSize = 28.sp
                        )

                        Text(
                            text = experience.description,
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Precio " + experience.price.toString() + "€",
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 14.sp
                        )
                    }

                }
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun ListPreview(){
    ListScreen(onExperienceClick = {})

//    ListScreen()
//    ListScreen(navController = NavController(LocalContext.current))
}
