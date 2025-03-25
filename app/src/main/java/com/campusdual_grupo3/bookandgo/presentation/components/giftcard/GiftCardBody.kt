package com.campusdual_grupo3.bookandgo.presentation.components.giftcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.campusdual_grupo3.bookandgo.R

@Composable
fun GiftCardBody(
    onPayClick: () -> Unit
) {
    val playfairFont = FontFamily(
        Font(R.font.playfair_regular, FontWeight.Normal)
    )

    var recipientName by remember { mutableStateOf("") }
    var recipientEmail by remember { mutableStateOf("") }
    var messageToRecipient by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {

       /* ---------- Input Name ↓ ---------- */
        OutlinedTextField(
            value = recipientName,
            onValueChange = { recipientName = it },
            label = { Text(stringResource(R.string.giftcard_input_name),  style = TextStyle(fontFamily = playfairFont)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        /* ---------- Input Email ↓ ---------- */
        OutlinedTextField(
            value = recipientEmail,
            onValueChange = { recipientEmail = it },
            label = { Text(stringResource(R.string.giftcard_input_email),  style = TextStyle(fontFamily = playfairFont)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        /* ---------- Input Message ↓ ---------- */
        OutlinedTextField(
            value = messageToRecipient,
            onValueChange = { messageToRecipient = it },
            label = { Text(stringResource(R.string.giftcard_input_message),  style = TextStyle(fontFamily = playfairFont)) },
            modifier = Modifier.fillMaxWidth().height(100.dp),
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(32.dp))

        /* ---------- Button to Checkout ↓ ---------- */
        Button(
            onClick = onPayClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ), shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(R.string.giftcard_button_checkout),
                style = TextStyle(fontFamily = playfairFont)
                )
        }
    }
}
