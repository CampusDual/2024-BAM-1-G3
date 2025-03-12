package com.campusdual_grupo3.bookandgo.presentation.experiencia

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.campusdual_grupo3.bookandgo.R

@Composable
fun ExperienceDetailScreen(
    experienceId: Int,
    onBackClick:() -> Unit
) {
    val viewModel: ExperienceViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.loadExperienceById(experienceId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
            Image(painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onBackClick()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp)

            )

        }

        Text(
            text = uiState.detailExperiences?.name ?: "${uiState.detailExperiences?.name}?"
        )
        Text(
            text = uiState.detailExperiences?.description ?: "Cargando..."
        )
        Text(
            text = uiState.detailExperiences?.price.toString() ?: "Cargando..."
        )
        Text(
            text = uiState.detailExperiences?.image ?: "Cargando..."
        )
        Text(
            text = uiState.detailExperiences?.name.toString()
        )


    }
}
