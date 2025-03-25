package com.campusdual_grupo3.bookandgo.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusdual_grupo3.bookandgo.R

@Preview
@Composable
fun CustomSearchBarPreview(){
    CustomSearchBar(onValueChange = {})
}

@Composable
fun CustomSearchBar(onValueChange: (String) -> Unit,
){
    var text by remember { mutableStateOf("") }

    val playfairFont = FontFamily(
        Font(R.font.playfair_regular, FontWeight.Normal)
    )
    Row(
            //***** barra buscador *****
            modifier = Modifier
                .padding(
                    horizontal = 10.dp, vertical = 10.dp
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
           TextField(
               colors = TextFieldDefaults.colors(
                   focusedContainerColor = Color.Transparent,   // Fondo cuando está enfocado
                   unfocusedContainerColor = Color.LightGray  // Fondo cuando NO está enfocado
               ),
               value = text,
               onValueChange = {
                text = it
                onValueChange(it)
                },
               modifier = Modifier
                   .weight(1f),
               singleLine = true,
               placeholder = {
                   Text(
                       text = stringResource(R.string.search_experience),
                       textAlign = TextAlign.Center,
                       style = TextStyle(fontFamily = playfairFont),
                       modifier = Modifier
                           .weight(1f)
                           .padding(
                           ),
                   )
               }
           )

            Image(
                painter = painterResource(id = R.drawable.ic_search_white),
                contentDescription = null,

                modifier = Modifier
                    .padding(
                        start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp
                    )

                    .background(color = Color.Black, shape = CircleShape)
                    .width(60.dp)
            )

        }
}