package com.example.wordsnack.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
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

    @Query("DELETE FROM word_tbl WHERE id = :id")
    suspend fun deleteWord(id: Int)
}
