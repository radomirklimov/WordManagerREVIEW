package com.example.wordsnack.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val newWordDao = WordDatabase.getDatabase(application).newWordDao()

    private val _words = MutableStateFlow<List<WordEntity>>(emptyList())
    val words: StateFlow<List<WordEntity>> = _words

    private val _sortOption = MutableStateFlow("newest")

    init {
        // Initial load of words from the database
        viewModelScope.launch {
            _words.value = getAllWords()
        }
    }

    // Insert a new word and update the list
    fun insertNewWord(word: String) {
        val newWord = WordEntity(word = word, timestamp = System.currentTimeMillis())
        viewModelScope.launch {
            newWordDao.insert(newWord)
            // Refresh the words list after insertion
            _words.value = getAllWords()
        }
    }

    suspend fun getWordById(id: Int): WordEntity? {
        return newWordDao.getWordById(id)
    }

    fun getSortedOption(option: String) {
        _sortOption.value = option
        viewModelScope.launch {
            _words.value = getAllWords()
        }
    }

    private suspend fun getAllWords(): List<WordEntity> {
        return when (_sortOption.value) {
            "newest" -> newWordDao.getWordsNewest()
            "oldest" -> newWordDao.getWordsOldest()
            "alphabetical" -> newWordDao.getWordsAlphabetical()
            else -> newWordDao.getWordsNewest()
        }
    }

    fun deleteWord(id: Int) {
        viewModelScope.launch {
            newWordDao.deleteWord(id)
            // Refresh the words list after insertion
            _words.value = getAllWords()
        }
    }
}
