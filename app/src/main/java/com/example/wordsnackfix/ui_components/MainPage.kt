package com.example.wordsnack.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordsnackfix.R
import com.example.wordsnack.room.WordEntity
import com.example.wordsnack.room.WordViewModel
import kotlin.math.min
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.example.wordsnackfix.navigation.Screen

object MainPage {

    @Composable
    fun mainPageMix(viewModel: WordViewModel, navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            addNewWord(viewModel)
            sortedType(viewModel)
            displayWords(viewModel, navController)
        }
    }

    @Composable
    fun addNewWord(viewModel: WordViewModel) {
        var showPopUp = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
                .height(120.dp)
        ) {

            Button(
                onClick = { showPopUp.value = true },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
                    .size(300.dp, 80.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .size(50.dp)
                )
                Text(
                    text = "Add new word",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
            }
            AddWordPopUp.windowUp(showPopUp, viewModel)

        }
    }

    @Composable
    fun sortedType(viewModel: WordViewModel) {
        val radioOptions = listOf("newest ", "oldest", "alphabetical")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

        Column(
            modifier = Modifier.background(Color.LightGray)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                radioOptions.forEach { text ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .height(IntrinsicSize.Min)
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                                viewModel.getSortedOption(text)
                            }
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun displayWords(viewModel: WordViewModel, navController: NavController) {
        val words by viewModel.words.collectAsState(initial = emptyList())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 50.dp)
        ) {
            for (word in words) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .background(Color.White)
                        .padding(10.dp)
//                        .clickable {
//                            navController.navigate(
//                                Screen.DetailScreen.createRoute(
//                                    word.word,
//                                    word.partOfSpeech,
//                                    word.transcription,
//                                    word.translation
//                                )
//                            )
//                        }
                ) {
                    Text(
                        text = word.word,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = word.translation,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = word.transcription,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "Delete Icon",
                        modifier = Modifier
                            .size(20.dp)
                            .weight(0.2f)
                            .clickable { viewModel.deleteWord(word.id) }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}
