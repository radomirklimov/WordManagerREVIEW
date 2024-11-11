package com.example.wordsnack.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.wordsnack.room.WordViewModel


object AddWordPopUp {
    @Composable
    fun windowUp(showPopUp: MutableState<Boolean>, viewModel: WordViewModel) {
        if (showPopUp.value) {
            Dialog(onDismissRequest = { showPopUp.value = false }) {
                Column(
                    Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(300.dp, 200.dp)
                        .background(color = Color.LightGray),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var word by remember { mutableStateOf("") }
                    var isError by remember { mutableStateOf(false) }

                    TextField(
                        value = word,
                        onValueChange = { word = it },
                        label = { Text("Enter your word") },
                        textStyle = TextStyle(color = Color.DarkGray, fontSize = 18.sp),
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .width(280.dp)
                            .height(56.dp)
                            .clip(RoundedCornerShape(40.dp))
                    )
                    Text(
                        text = "verb",
                        modifier = Modifier
                            .clip(RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp))
                            .background(Color.Yellow)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color.DarkGray
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 26.dp, start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { showPopUp.value = false },
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.DarkGray
                            ),
                            modifier = Modifier
                                .size(105.dp, 40.dp)
                        ) {
                            Text(
                                text = "cancel",
                                color = Color(0xFFF9B4B4),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }

                        Button(
                            onClick = { viewModel.insertNewWord(word) ; showPopUp.value = false },
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.DarkGray
                            ),
                            modifier = Modifier
                                .size(115.dp, 40.dp)
                        )
                        {
                            Text(
                                text = "add",
                                color = Color(0xFFBDF9B4),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}



