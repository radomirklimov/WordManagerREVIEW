package com.example.wordsnackfix.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wordsnackfix.R

object WordPage {
    @Composable
    fun TheWord(
        navController: NavController,
        word: String,
        partOfSpeech: String,
        transcription: String,
        translation: String
    ){
        Box {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.Yellow)
                    .padding(10.dp)
            ) {
                Text(text = word,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .background(color = Color.Red)
                        .padding(10.dp)
                )
                Text(text = partOfSpeech)
                Text(text = transcription)
                Text(text = translation)
            }

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
                modifier = Modifier
                    .padding(10.dp)
                    .size(60.dp, 30.dp)

            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .size(70.dp)

                )

            }
        }
    }
}