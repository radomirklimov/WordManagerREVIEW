package com.example.wordsnackfix.ui_components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
    ) {
        var isFlipped by remember { mutableStateOf(false) }
        // Rotate from 0 to 180 or vice versa based on flip state
        val rotation by animateFloatAsState(targetValue = if (isFlipped) 1f else 0f)

        Box {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.LightGray)
                    .padding(10.dp, top = 30.dp)
            ) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { isFlipped = !isFlipped }
                        .rotate(rotation),
                    shape = RoundedCornerShape(20),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    if (!isFlipped){
                        Text(
                            text = word,
                            fontSize = 28.sp,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }else{
                        Text(
                            text = translation,
                            fontSize = 28.sp,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }
                }

                Text(text = partOfSpeech)
                Text(text = transcription)
            }

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .padding(10.dp, top = 30.dp)
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