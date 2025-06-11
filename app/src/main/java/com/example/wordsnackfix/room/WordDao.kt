package com.example.wordsnack.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Insert
    suspend fun insert(wordEntity: WordEntity)

    @Query("SELECT * FROM word_tbl WHERE id = :id")
    suspend fun getWordById(id: Int): WordEntity?

    @Query("SELECT * FROM word_tbl")
    suspend fun getAllWords(): List<WordEntity>

    @Query("SELECT * FROM word_tbl ORDER BY timestamp DESC")
    suspend fun getWordsNewest(): List<WordEntity>

    @Query("SELECT * FROM word_tbl ORDER BY timestamp ASC")
    suspend fun getWordsOldest(): List<WordEntity>

    @Query("SELECT * FROM word_tbl ORDER BY word ASC")
    suspend fun getWordsAlphabetical(): List<WordEntity>

    @Query("DELETE FROM word_tbl WHERE id = :id")
    suspend fun deleteWord(id: Int)
}
