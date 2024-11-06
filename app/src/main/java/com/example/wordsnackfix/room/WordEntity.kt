package com.example.wordsnack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_tbl")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val word: String,
    val partOfSpeech: String = "unspecified",
    val transcription: String = "[not found]",
    val translation: String = "[not found]",
    val timestamp: Long = System.currentTimeMillis()
)
